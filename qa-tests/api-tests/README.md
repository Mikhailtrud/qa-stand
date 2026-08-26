# API tests

JUnit 5 API tests for QA Sandbox, using REST Assured, AssertJ, Jackson, and
Allure.

## Local workflow

Requirements:

- Java 21;
- backend available at `http://localhost:8080` by default;
- PostgreSQL available at `localhost:5432` with the defaults below.

1. From the repository root, start the required services:

```powershell
docker compose up -d postgres backend
```

2. From `qa-tests/api-tests`, run the tests:

```powershell
.\gradlew.bat test
```

3. Generate a fresh static Allure report:

```powershell
.\gradlew.bat allureReport --clean
```

4. Open the report:

```powershell
.\build\allure\commandline\bin\allure.bat open ..\reports\api\allure-report
```

`test` uses JUnit Platform discovery for classes under `src/test/java`. The
current source tree does not contain `ApiTestSuite`, so tests are not also
selected through a suite. The verified run executes 21 tests: 21 passed and 0
failed, including both raw string ID scenarios.

## Allure report

The Java adapter writes raw data to:

```text
build/allure-results
```

Generate the static report after a test run with:

```powershell
.\gradlew.bat allureReport --clean
```

The configured `allureReport` task reads `build/allure-results` and writes the
finished report to:

```text
../reports/api/allure-report
```

Open the finished report with:

```powershell
.\build\allure\commandline\bin\allure.bat open ..\reports\api\allure-report
```

The `--clean` option removes the previous finished report before generation;
it does not move or remove raw results. No manual result copying is required.

`allureServe` is not the primary workflow because the retained report belongs
under `qa-tests/reports`, outside `api-tests/build`.

## Test architecture

```text
src/test/java/ru/mikhail/qasandbox/
├── assertions/      # custom assertions for API responses
├── base/            # common JUnit setup and authentication
├── client/          # HTTP client layer
├── config/          # runtime and environment configuration
├── data/
│   ├── builder/     # request test-data builders
│   └── testData/    # scenario constants
├── db/              # JDBC connection factory
├── dto/             # request and response records
├── specifications/  # shared REST Assured request specification
└── tests/           # API scenarios discovered by JUnit Platform
```

### Base classes

- `BaseTest` creates `AuthClient`, `UsersClient`, and `DbClient` before each
  test. Creating `DbClient` stores the DB settings; a connection is opened only
  when `getConnection()` is called.
- `AuthenticatedTest` logs in with `AdminTestData`, verifies HTTP 200, extracts
  the token, and passes it to `UsersClient.setToken()`. `BaseApiClient` then
  adds it as `Authorization: Bearer <token>`.

### API client layer

- `BaseApiClient` builds requests and implements shared GET, POST, PUT, and
  DELETE execution.
- `AuthClient` exposes `/auth/login`.
- `UsersClient` exposes typed `/users` operations and raw-ID methods used by
  negative path-parameter tests.
- `ApiResponse<T>` wraps the REST Assured response and provides status, typed
  success body, and `ErrorResponse` deserialization.

### Test data and configuration

`UserBuilder` and `EditUserBuilder` create valid requests and allow individual
fields to be overridden for negative tests. Both generate email addresses with
`UUID`, so created users do not reuse a fixed email.

Scenario values such as invalid email, empty fields, passwords, and roles are
in `data/testData`. `AdminTestData` builds the login request from
`UserDataConfig`.

Environment/configuration data is read in this order: Java system property,
environment variable, then the code default.

| Purpose | System property | Environment variable | Default |
|---|---|---|---|
| API URL | `baseUrl` | `BASE_URL` | `http://localhost:8080` |
| DB URL | `db.url` | `DB_URL` | `jdbc:postgresql://localhost:5432/qasandbox` |
| DB user | `db.user` | `DB_USER` | `qauser` |
| DB password | `db.password` | `DB_PASSWORD` | `qapass` |
| Admin email | `admin.email` | `ADMIN_EMAIL` | `test@email.com` |
| Admin password | `admin.password` | `ADMIN_PASSWORD` | `admin123` |
| Admin role | `admin.role` | `ADMIN_ROLE` | `ADMIN` |

## Logging

The shared request specification logs the HTTP method for every request.
`BaseApiClient` asks REST Assured to log a response only if REST Assured
validation fails. Therefore a normal successful console run should not contain
full request headers or bodies from this configuration.

`AllureRestAssured` attaches request and response details to the report. Those
attachments can contain `Authorization` headers and login payloads, including
passwords. Treat generated Allure results/reports and verbose diagnostic logs
as sensitive; do not publish them without checking their contents.

## Cleanup

Tests that create a user retain its ID and delete it in `@AfterEach`.

- Create, edit, and list scenarios currently require cleanup DELETE to return
  `200`.
- Get-by-ID and delete scenarios accept `200` or `404`, because the scenario
  may already have deleted that user before teardown runs.
- When creation never produced an ID, cleanup is skipped.

These are the cleanup expectations implemented by the current tests.

## JVM memory

`maxHeapSize = "2g"` gives the forked test JVM enough memory for the current
Allure/AspectJ-instrumented test run; lower memory previously caused AspectJ
`OutOfMemoryError`.

`org.gradle.jvmargs=-Xmx2g` gives the Gradle process enough memory while it
configures, instruments, and runs this project. It complements the test JVM
setting; it does not replace it.

## Docker

From the repository root, run the end-to-end Docker workflow with:

```powershell
docker compose -f docker-compose.ci.yml up --build --abort-on-container-exit
```

Compose starts PostgreSQL and waits for its health check, starts the backend
and waits for its health check, then starts the frontend and the API tests in
a dedicated container with `BASE_URL=http://backend:8080`.

The API test container runs `./gradlew clean test` and, after a successful
test run, copies `build/allure-results` to the mounted
`/reports/api/allure-results` directory. On the host, the raw results are
available at:

```text
qa-tests/reports/api/allure-results
```

## Extended stand examples

The example tests for password rules, optional-password user updates, Redis,
RabbitMQ/audit delivery, external profiles, and the cross-service user lifecycle
require the extended local stand. Start it from the repository root:

```bash
docker compose -f docker-compose.yaml up --build -d
```

Alternatively, run the destructive clean smoke validation first:

```bash
bash scripts/verify-extended-stand.sh
```

Then run all API tests from `qa-tests/api-tests`:

```bash
./gradlew test
```

Run only the new examples:

```bash
./gradlew test \
  --tests '*UserPasswordValidationTest' \
  --tests '*UpdateUserTest' \
  --tests '*UserCacheIntegrationTest' \
  --tests '*UserAuditIntegrationTest' \
  --tests '*ExternalProfileTest' \
  --tests '*UserLifecycleIntegrationTest'
```

Integration configuration follows the existing system-property, environment,
default precedence:

| Purpose | System property | Environment variable | Default |
|---|---|---|---|
| Audit API | `audit.service.url` | `AUDIT_SERVICE_URL` | `http://localhost:8090` |
| Redis host | `redis.host` | `REDIS_HOST` | `localhost` |
| Redis port | `redis.port` | `REDIS_PORT` | `6379` |

The audit examples poll `GET /audit/events` with Awaitility. Redis examples use
the public Redis protocol and the implemented `users:{id}` key, not Spring
internals. External-profile examples call only the backend endpoint and use the
deterministic IDs defined by the repository's WireMock mappings.
