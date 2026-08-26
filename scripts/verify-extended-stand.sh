#!/usr/bin/env bash
set -Eeuo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

COMPOSE=(docker compose -f docker-compose.yaml)
TMP_DIR="$(mktemp -d)"
RESPONSE_BODY="$TMP_DIR/response.json"
ADMIN_TOKEN=""
TEST_USER_ID=""
CURRENT_SUBSYSTEM="startup"

cleanup() {
  if [[ -n "$TEST_USER_ID" && -n "$ADMIN_TOKEN" ]]; then
    curl --silent --output /dev/null --max-time 5 \
      -X DELETE -H "Authorization: Bearer $ADMIN_TOKEN" \
      "http://localhost:8080/users/$TEST_USER_ID" || true
  fi
  rm -rf "$TMP_DIR"
}
trap cleanup EXIT

show_diagnostics() {
  echo >&2
  echo "Compose status:" >&2
  "${COMPOSE[@]}" ps >&2 || true
  echo >&2
  echo "Relevant container logs (last 120 lines):" >&2
  "${COMPOSE[@]}" logs --no-color --tail=120 \
    backend audit-service mock-service rabbitmq redis postgres audit-postgres >&2 || true
}

fail() {
  local subsystem="$1"
  local message="$2"
  echo "[FAIL] $subsystem: $message" >&2
  if [[ -s "$RESPONSE_BODY" ]]; then
    echo "Response body:" >&2
    cat "$RESPONSE_BODY" >&2
    echo >&2
  fi
  show_diagnostics
  exit 1
}

unexpected_error() {
  local line="$1"
  local command="$2"
  fail "$CURRENT_SUBSYSTEM" "unexpected command failure at line $line: $command"
}
trap 'unexpected_error "$LINENO" "$BASH_COMMAND"' ERR

pass() {
  echo "[PASS] $1"
}

require_command() {
  command -v "$1" >/dev/null 2>&1 || fail "Prerequisites" "required command '$1' was not found"
}

http_request() {
  local method="$1"
  local url="$2"
  local body="${3:-}"
  local token="${4:-}"
  local args=(--silent --show-error --output "$RESPONSE_BODY" --write-out '%{http_code}'
    --connect-timeout 3 --max-time 10 -X "$method")
  [[ -z "$token" ]] || args+=(-H "Authorization: Bearer $token")
  if [[ -n "$body" ]]; then
    args+=(-H 'Content-Type: application/json' --data "$body")
  fi
  HTTP_STATUS="$(curl "${args[@]}" "$url")"
}

expect_status() {
  local subsystem="$1"
  local expected="$2"
  [[ "$HTTP_STATUS" == "$expected" ]] || fail "$subsystem" "expected HTTP $expected, got $HTTP_STATUS"
}

wait_for_http() {
  local subsystem="$1"
  local url="$2"
  local attempts="${3:-90}"
  for ((attempt = 1; attempt <= attempts; attempt++)); do
    if curl --silent --fail --output /dev/null --connect-timeout 2 --max-time 3 "$url"; then
      return 0
    fi
    sleep 2
  done
  fail "$subsystem" "timed out waiting for $url"
}

for command_name in docker curl jq; do
  require_command "$command_name"
done
docker compose version >/dev/null 2>&1 || fail "Prerequisites" "Docker Compose v2 is required"
docker info >/dev/null 2>&1 || fail "Prerequisites" "Docker daemon is unavailable"

CURRENT_SUBSYSTEM="Clean start"
echo "Recreating the extended stand (this removes only this Compose project's containers and volumes)..."
"${COMPOSE[@]}" down --remove-orphans --volumes
"${COMPOSE[@]}" up --detach --build --wait --wait-timeout 240 \
  || fail "Clean start" "docker compose up failed"
pass "Clean start"

CURRENT_SUBSYSTEM="Service readiness"
wait_for_http "Backend readiness" "http://localhost:8080/actuator/health"
wait_for_http "Audit service readiness" "http://localhost:8090/audit/events"
wait_for_http "Mock service readiness" "http://localhost:8089/__admin/mappings"
wait_for_http "Frontend readiness" "http://localhost:5173/"
"${COMPOSE[@]}" exec -T postgres pg_isready -U qauser -d qasandbox >/dev/null
"${COMPOSE[@]}" exec -T audit-postgres pg_isready -U audituser -d auditdb >/dev/null
"${COMPOSE[@]}" exec -T rabbitmq rabbitmq-diagnostics -q ping >/dev/null
[[ "$("${COMPOSE[@]}" exec -T redis redis-cli ping | tr -d '\r')" == "PONG" ]] \
  || fail "Service readiness" "Redis did not return PONG"
pass "Required services ready"

CURRENT_SUBSYSTEM="Backend login"
LOGIN_JSON='{"email":"test@email.com","password":"admin123"}'
http_request POST "http://localhost:8080/auth/login" "$LOGIN_JSON"
expect_status "Backend login" 200
ADMIN_TOKEN="$(jq -er '.token | select(length > 0)' "$RESPONSE_BODY")" \
  || fail "Backend login" "login response did not contain a token"
pass "Backend login"

STAMP="$(date +%s)-$$"
CREATE_EMAIL="smoke.$STAMP@example.com"
UPDATED_EMAIL="smoke.updated.$STAMP@example.com"

CURRENT_SUBSYSTEM="Password validation"
INVALID_JSON="$(jq -nc --arg email "invalid.$STAMP@example.com" \
  '{name:"Invalid Password",email:$email,password:"abc",role:"USER"}')"
http_request POST "http://localhost:8080/users" "$INVALID_JSON" "$ADMIN_TOKEN"
expect_status "Password validation" 400
jq -e '.errors.password' "$RESPONSE_BODY" >/dev/null \
  || fail "Password validation" "HTTP 400 response did not contain errors.password"
pass "Password validation"

CURRENT_SUBSYSTEM="User creation"
CREATE_JSON="$(jq -nc --arg email "$CREATE_EMAIL" \
  '{name:"Smoke User",email:$email,password:"Test1234",role:"USER"}')"
http_request POST "http://localhost:8080/users" "$CREATE_JSON" "$ADMIN_TOKEN"
expect_status "User creation" 201
TEST_USER_ID="$(jq -er '.id' "$RESPONSE_BODY")" \
  || fail "User creation" "create response did not contain a user ID"
pass "User creation"

CURRENT_SUBSYSTEM="Redis cache"
http_request GET "http://localhost:8080/users/$TEST_USER_ID" "" "$ADMIN_TOKEN"
expect_status "Redis cache" 200
CACHE_KEY="users:$TEST_USER_ID"
[[ "$("${COMPOSE[@]}" exec -T redis redis-cli EXISTS "$CACHE_KEY" | tr -d '\r')" == "1" ]] \
  || fail "Redis cache" "cache key '$CACHE_KEY' was not created after GET"

CURRENT_SUBSYSTEM="User update"
UPDATE_JSON="$(jq -nc --arg email "$UPDATED_EMAIL" \
  '{name:"Smoke User Updated",email:$email,role:"ADMIN"}')"
http_request PUT "http://localhost:8080/users/$TEST_USER_ID" "$UPDATE_JSON" "$ADMIN_TOKEN"
expect_status "User update" 200
[[ "$("${COMPOSE[@]}" exec -T redis redis-cli EXISTS "$CACHE_KEY" | tr -d '\r')" == "0" ]] \
  || fail "Redis cache" "cache key '$CACHE_KEY' was not invalidated by update"
pass "Redis cache"

http_request GET "http://localhost:8080/users/$TEST_USER_ID" "" "$ADMIN_TOKEN"
expect_status "User update" 200
jq -e --arg email "$UPDATED_EMAIL" \
  '.name == "Smoke User Updated" and .email == $email and .role == "ADMIN"' \
  "$RESPONSE_BODY" >/dev/null || fail "User update" "GET did not return the updated fields"

# Omitting password from PUT must preserve the original password hash.
UPDATED_LOGIN_JSON="$(jq -nc --arg email "$UPDATED_EMAIL" \
  '{email:$email,password:"Test1234"}')"
http_request POST "http://localhost:8080/auth/login" "$UPDATED_LOGIN_JSON"
expect_status "User update" 200
pass "User update"

CURRENT_SUBSYSTEM="RabbitMQ publish"
"${COMPOSE[@]}" exec -T rabbitmq rabbitmq-diagnostics -q ping >/dev/null
pass "RabbitMQ publish"

CURRENT_SUBSYSTEM="Audit consumer"
AUDIT_MATCHED=false
for ((attempt = 1; attempt <= 30; attempt++)); do
  http_request GET "http://localhost:8090/audit/events"
  if [[ "$HTTP_STATUS" == "200" ]] && jq -e --argjson id "$TEST_USER_ID" '
      ([.[] | select(.userId == $id and .eventType == "USER_CREATED")] | length) >= 1
      and ([.[] | select(.userId == $id and .eventType == "USER_UPDATED")] | length) >= 1
    ' "$RESPONSE_BODY" >/dev/null; then
    AUDIT_MATCHED=true
    break
  fi
  sleep 2
done
[[ "$AUDIT_MATCHED" == "true" ]] \
  || fail "Audit consumer" "USER_CREATED and USER_UPDATED were not observed within 60 seconds"
pass "Audit consumer"

CURRENT_SUBSYSTEM="Audit persistence"
AUDIT_COUNT="$("${COMPOSE[@]}" exec -T audit-postgres psql -U audituser -d auditdb -Atc \
  "SELECT COUNT(*) FROM audit_events WHERE user_id = $TEST_USER_ID AND event_type IN ('USER_CREATED', 'USER_UPDATED');" | tr -d '[:space:]')"
[[ "$AUDIT_COUNT" =~ ^[0-9]+$ && "$AUDIT_COUNT" -ge 2 ]] \
  || fail "Audit persistence" "expected at least 2 persisted events, found '$AUDIT_COUNT'"
pass "Audit persistence"

CURRENT_SUBSYSTEM="WireMock success"
http_request GET "http://localhost:8080/users/1/external-profile" "" "$ADMIN_TOKEN"
expect_status "WireMock success" 200
jq -e '.userId == 1 and .status == "ACTIVE" and .score == 85' "$RESPONSE_BODY" >/dev/null \
  || fail "WireMock success" "unexpected success payload"
pass "WireMock success"

CURRENT_SUBSYSTEM="WireMock 404"
http_request GET "http://localhost:8080/users/404/external-profile" "" "$ADMIN_TOKEN"
expect_status "WireMock 404" 404
pass "WireMock 404"

CURRENT_SUBSYSTEM="WireMock 500"
http_request GET "http://localhost:8080/users/500/external-profile" "" "$ADMIN_TOKEN"
expect_status "WireMock 500" 502
pass "WireMock 500"

CURRENT_SUBSYSTEM="WireMock timeout"
START_SECONDS="$(date +%s)"
http_request GET "http://localhost:8080/users/999/external-profile" "" "$ADMIN_TOKEN"
ELAPSED_SECONDS=$(( $(date +%s) - START_SECONDS ))
expect_status "WireMock timeout" 504
(( ELAPSED_SECONDS < 8 )) \
  || fail "WireMock timeout" "backend took ${ELAPSED_SECONDS}s instead of enforcing its configured timeout"
pass "WireMock timeout"

CURRENT_SUBSYSTEM="Frontend"
http_request GET "http://localhost:5173/"
expect_status "Frontend" 200
pass "Frontend"

CURRENT_SUBSYSTEM="Temporary user cleanup"
http_request DELETE "http://localhost:8080/users/$TEST_USER_ID" "" "$ADMIN_TOKEN"
expect_status "Temporary user cleanup" 200
TEST_USER_ID=""
pass "Temporary user cleanup"

echo "--------------------------------"
echo "EXTENDED STAND SMOKE TEST PASSED"
