# Backend

Java 21 / Spring Boot 3.5 backend for QA Stand.

## Responsibilities

- bearer-token authentication with ADMIN and USER roles;
- BCrypt password storage and request validation;
- PostgreSQL users as the source of truth, managed by Flyway;
- optional Redis caching for `GET /users/{id}`;
- optional RabbitMQ `USER_CREATED` and `USER_UPDATED` publishing;
- configured HTTP client for external user profiles;
- OpenAPI/Swagger and Actuator health endpoints.

Redis and RabbitMQ integrations default to disabled when the backend is run by
itself. `docker-compose.yaml` enables both and supplies Docker service hostnames.
Their Actuator health contributors follow the same flags, which keeps isolated
API and mobile environments usable without those services.

## Run and build

With PostgreSQL available at the defaults in `application.yaml`:

```bash
./gradlew bootRun
./gradlew test
./gradlew build
```

For the fully integrated runtime, run from the repository root:

```bash
docker compose -f docker-compose.yaml up --build -d
```

Swagger UI: <http://localhost:8080/swagger-ui.html>

## API

Authentication:

```http
POST /auth/login
```

Users:

```http
GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}
GET    /users/{id}/external-profile
```

All user endpoints require a bearer token. Reads allow ADMIN or USER; POST,
PUT, and DELETE require ADMIN.

Create passwords are required and must be at least eight characters with one
letter and one digit. PUT edits `name`, `email`, and `role`; `password` is
optional. Null, omitted, empty, or whitespace-only update passwords preserve
the stored BCrypt hash. Invalid fields return HTTP 400 with a field-level
`errors` map. Duplicate emails return HTTP 409 and missing users return 404.

The seeded QA administrator is `test@email.com` / `admin123` with role ADMIN.

## Redis

Set `USER_CACHE_ENABLED=true` to cache password-free `UserResponse` values.
Keys use `users:{id}`. `USER_CACHE_TTL` defaults to `5m`; PUT and DELETE evict
the corresponding key. Redis failures fall back to PostgreSQL for reads, and
PostgreSQL remains authoritative. Passwords, tokens, and authentication
requests are not cached.

## RabbitMQ

Set `USER_EVENTS_ENABLED=true` to publish JSON events after successful creates
and updates.

```text
exchange:    qa.user.events
routing key: user.changed
event types: USER_CREATED, USER_UPDATED
```

Payload fields are `eventType`, `userId`, `email`, `role`, and `timestamp`.
The separate audit service owns queue `qa.audit.user-events` and persistence;
the backend has no direct dependency on audit-service internals or its database.

## External profiles

`EXTERNAL_PROFILE_BASE_URL` defaults to `http://localhost:8089`; Compose sets it
to `http://mock-service:8080`. `EXTERNAL_PROFILE_TIMEOUT` defaults to `2s`.
The client maps external 404 to backend 404, external 5xx to 502, and connection
or read timeout to 504.

## Database migrations

Backend migrations are under `src/main/resources/db/migration`:

```text
V1__create_users_table.sql
V2__add_password_to_users.sql
V3__seed_default_admin.sql
```

Audit database migrations belong to `services/audit-service`; the backend does
not access that database.

## Source structure

```text
src/main/java/com/qasandbox/backend/
|-- cache/       # Redis/no-op user cache adapters
|-- client/      # external-profile HTTP client
|-- config/      # HTTP, Redis, RabbitMQ, CORS, OpenAPI
|-- controller/
|-- dto/
|-- entity/
|-- event/       # user event contract and publisher
|-- exception/
|-- mapper/
|-- repository/
|-- security/
`-- service/
```
