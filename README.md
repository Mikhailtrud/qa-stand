# QA Stand

QA Stand is a training project for API, UI, mobile, database, and integration
testing. It combines a React UI, a Spring Boot users API, infrastructure
integrations, a small audit microservice, deterministic external-service mocks,
and a separate Linux mobile-test stack.

## Architecture

The normal development stack is defined by `docker-compose.yaml`:

```text
Browser
  |
  v
Frontend (React/Vite :5173)
  |
  v
Backend (Spring Boot :8080)
  |------> PostgreSQL (:5432)       primary users data
  |------> Redis (:6379)            user-response cache
  |------> WireMock (:8089)         external profile scenarios
  `------> RabbitMQ (:5672)
                |
                v
          Audit service (:8090)
                |
                v
          Audit PostgreSQL (:5433)
```

The Android emulator/Appium workflow is separate and uses
`docker-compose.mobile-ci.yml`; it does not start the frontend, Redis,
RabbitMQ, audit service, or WireMock.

## Services and ports

| Compose service | Container | Exposed port | Purpose | Important dependencies |
|---|---|---:|---|---|
| `frontend` | `qa-frontend` | 5173 | React/Vite web UI | backend |
| `backend` | `qa-backend` | 8080 | Authentication, users, cache/events, external profile API | postgres, Redis, RabbitMQ, mock-service |
| `postgres` | `qa-postgres` | 5432 | Backend source-of-truth database | none |
| `redis` | `qa-redis` | 6379 | Cache for individual user responses | none |
| `rabbitmq` | `qa-rabbitmq` | 5672, 15672 | User-event broker and management UI | none |
| `audit-service` | `qa-audit-service` | 8090 | Consumes and exposes persisted user events | RabbitMQ, audit-postgres |
| `audit-postgres` | `qa-audit-postgres` | 5433 | Isolated audit-event database | none |
| `mock-service` | `qa-mock-service` | 8089 | WireMock external-profile dependency | none |

RabbitMQ management is available at <http://localhost:15672> with the image's
default development credentials (`guest` / `guest`). Swagger UI is available
at <http://localhost:8080/swagger-ui.html>.

## Start and stop

Start the complete extended development stand:

```bash
docker compose -f docker-compose.yaml up --build -d
```

The same Compose file can start an API/UI-focused subset; Compose also starts
the backend's declared infrastructure dependencies:

```bash
docker compose -f docker-compose.yaml up --build -d postgres backend frontend
```

Stop the stand while retaining PostgreSQL data:

```bash
docker compose -f docker-compose.yaml down --remove-orphans
```

Remove the stand and both Compose-managed PostgreSQL volumes:

```bash
docker compose -f docker-compose.yaml down --remove-orphans --volumes
```

## Authentication and users

The deterministic QA administrator is seeded by Flyway:

```text
email:    test@email.com
password: admin123
role:     ADMIN
```

`POST /auth/login` returns a bearer token. Read endpoints allow ADMIN and USER;
creating, editing, and deleting users require ADMIN.

| Method and path | Behavior |
|---|---|
| `POST /auth/login` | Authenticate by email/password |
| `GET /users` | List users |
| `GET /users/{id}` | Read one user; Redis-backed when enabled |
| `POST /users` | Create a user |
| `PUT /users/{id}` | Edit an existing user |
| `DELETE /users/{id}` | Delete a user |
| `GET /users/{id}/external-profile` | Request an external profile through the configured mock/service |

Create requires `name`, `email`, `role`, and `password`. Passwords must be at
least eight characters and contain at least one letter and one digit. Invalid
request fields return HTTP 400 with an `errors` object. Passwords are stored as
BCrypt hashes.

Edit supports `name`, `email`, `role`, and an optional `password`. A null,
omitted, empty, or whitespace-only password preserves the existing BCrypt
hash. A supplied password must satisfy the same rules as create. Duplicate
email, missing user, and invalid role/password responses retain the backend's
normal error handling. The Users page provides Create, Edit, Cancel, Save, and
Delete actions and displays backend validation messages.

## Redis cache

When `USER_CACHE_ENABLED=true`, `GET /users/{id}` caches the password-free
`UserResponse` under:

```text
users:{id}
```

The TTL is configured by `USER_CACHE_TTL` and is `5m` in the development
Compose stack. Update and delete evict `users:{id}`. A later GET repopulates it
from PostgreSQL. PostgreSQL remains the source of truth; passwords, login
requests, tokens, and other authentication data are never cached.

## RabbitMQ user events

The development Compose enables publishing with these configured values:

| Setting | Value |
|---|---|
| Exchange | `qa.user.events` |
| Queue | `qa.audit.user-events` |
| Routing key | `user.changed` |
| Event types | `USER_CREATED`, `USER_UPDATED` |

Messages are JSON:

```json
{
  "eventType": "USER_UPDATED",
  "userId": 1,
  "email": "test@email.com",
  "role": "ADMIN",
  "timestamp": "2026-08-25T12:00:00Z"
}
```

RabbitMQ is an explicit QA integration point for validating message publishing,
routing, asynchronous consumption, retry timing, and cross-service data. It is
not used to replace normal synchronous backend operations.

## Audit service

`services/audit-service` is an independent Java 21/Spring Boot application. It
does not call or import the backend. It consumes `USER_CREATED` and
`USER_UPDATED` messages from `qa.audit.user-events`, stores them in its own
PostgreSQL `audit_events` table, and exposes:

```http
GET http://localhost:8090/audit/events
```

Results are ordered by receive time, newest first. Its database is isolated in
the `audit-postgres` service (`auditdb`, host port 5433) and managed by the
audit service's own Flyway migration.

## WireMock external profiles

The backend uses `EXTERNAL_PROFILE_BASE_URL`; Compose sets it to
`http://mock-service:8080`. Versioned mappings under `mock-service/mappings`
provide these scenarios:

| Backend request | WireMock request | Backend result |
|---|---|---|
| `GET /users/1/external-profile` | `GET /external/users/1` | HTTP 200, `ACTIVE`, score 85 |
| `GET /users/404/external-profile` | `GET /external/users/404` | HTTP 404 |
| `GET /users/500/external-profile` | `GET /external/users/500` | WireMock 500 mapped to HTTP 502 |
| `GET /users/999/external-profile` | `GET /external/users/999` | Five-second delay; backend's two-second timeout maps to HTTP 504 |

These deterministic outcomes are intended for future API and integration test
scenarios; backend timeout and error mapping remain active.

## Automated validation and tests

Validate the complete extended runtime on Linux/WSL with Docker, Compose v2,
`curl`, and `jq`:

```bash
bash scripts/verify-extended-stand.sh
```

The script recreates only this Compose project and its volumes, waits for all
services, authenticates, validates password rules and user editing, observes
Redis population/invalidation, waits for RabbitMQ events in the audit API,
queries audit PostgreSQL, exercises all WireMock outcomes through the backend,
checks the frontend, and deletes its temporary user. On failure it prints the
relevant container logs. Because it uses `down --volumes` for a clean database,
do not run it against development data that must be retained.

Existing automation areas:

- `qa-tests/api-tests`: Java 21, JUnit 5, REST Assured, JDBC, and Allure API/DB tests.
- `qa-tests/ui-tests`: Java 21, JUnit 5, Selenide, and Allure browser tests.
- `qa-tests/mobile-tests`: Java 21, Appium Java client, backend helper, and Allure mobile tests.
- `docker-compose.ci.yml`: existing containerized API-test workflow.
- `docker-compose.mobile-ci.yml`: Linux/KVM Android mobile workflow described below.

No general CI/CD pipeline for the extended architecture is implemented yet.

## Linux mobile CI

The mobile stack is intentionally isolated in `docker-compose.mobile-ci.yml`.
It starts PostgreSQL and the backend, builds `app-debug.apk`, runs Google's
pinned API 30 / Android 11 emulator with KVM, forwards emulator traffic to the
backend, waits for Android boot, installs and verifies
`com.qastand.android`, starts Appium 3.7.0 with UiAutomator2 8.5.0, and runs
`qa-tests/mobile-tests`. The frontend and extended RabbitMQ/Redis/audit/mock
services are not part of this flow.

Prerequisites:

- Linux Docker Engine with `/dev/kvm` available;
- Docker Compose v2;
- an ADB private key shared with the emulator, installer, and Appium.

```bash
ls -l /dev/kvm
export ANDROID_ADB_PRIVATE_KEY="$(cat ~/.android/adbkey)"
export ADMIN_EMAIL='test@email.com'
export ADMIN_PASSWORD='admin123'
```

Build the stack, run mobile tests, and return the test container's exit code:

```bash
docker compose -f docker-compose.mobile-ci.yml --profile tests up \
  --build --abort-on-container-exit --exit-code-from mobile-tests
docker compose -f docker-compose.mobile-ci.yml --profile tests down
```

Start only the persistent mobile infrastructure:

```bash
docker compose -f docker-compose.mobile-ci.yml up --build -d
```

Stop it with:

```bash
docker compose -f docker-compose.mobile-ci.yml down
```

Add `-v` only when the mobile PostgreSQL and APK volumes should also be removed.
`ANDROID_ADB_PRIVATE_KEY` must contain the private key contents, not its path.
The APK uses `http://10.0.2.2:8080/`; the emulator-network backend proxy forwards
that address to Compose service `backend:8080`. Mobile test containers use
`http://appium:4723` and `http://backend:8080` directly. `PLATFORM_VERSION` is
unset by default so Appium selects the connected Android 11 emulator.

## Repository structure

```text
qa-stand/
|-- backend/                    # Spring Boot API, Flyway, Redis/Rabbit clients
|-- frontend/                   # React/Vite UI
|-- android-app/                # Android application
|-- services/
|   `-- audit-service/          # RabbitMQ consumer, audit API, own Flyway project
|-- mock-service/
|   `-- mappings/               # Versioned WireMock scenarios
|-- qa-tests/
|   |-- api-tests/
|   |-- ui-tests/
|   `-- mobile-tests/
|-- mobile-ci/                  # Appium image and emulator preparation scripts
|-- scripts/
|   `-- verify-extended-stand.sh
|-- docker-compose.yaml         # Extended local development stand
|-- docker-compose.ci.yml       # Existing API test containers
`-- docker-compose.mobile-ci.yml # Isolated Linux/KVM mobile stack
```

Component-specific instructions are in `backend/README.md`,
`frontend/README.md`, and the READMEs under `qa-tests`.
