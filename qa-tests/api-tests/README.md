# API Tests

API automation framework for QA Sandbox.

## Tech Stack

- Java 21
- Gradle
- JUnit 5
- REST Assured
- AssertJ
- Jackson
- DataFaker
- Allure Report
- Docker

---

## Project Structure

```
src
├── test
│   ├── java
│   │   └── ru.mikhail.qasandbox
│   │       ├── base
│   │       ├── client
│   │       ├── config
│   │       ├── data
│   │       ├── dto
│   │       ├── specifications
│   │       ├── tests
│   │       └── utils
│   │
│   └── resources
```

---

## Run tests

### IntelliJ IDEA

```
Gradle
└── verification
    └── test
```

### Command line

```bash
./gradlew clean test
```

Windows

```powershell
.\gradlew.bat clean test
```

---

## Allure

Results are generated into:

```
allure-results/
```

Generate HTML report:

```bash
allure generate allure-results --clean -o ../../reports/api/allure-report
```

Open report:

```bash
allure open ../../reports/api/allure-report
```

---

## Docker CI

Run:

```bash
docker compose -f ../../docker-compose.ci.yml up --build
```

After execution:

```
reports/
└── api
    ├── allure-results
    └── allure-report
```

---

## Configuration

Default values:

| Property | Default |
|----------|---------|
| BASE_URL | http://localhost:8080 |
| ADMIN_EMAIL | test@email.com |
| ADMIN_PASSWORD | admin123 |
| ADMIN_ROLE | ADMIN |

Values can be overridden via environment variables.

---

## Status

Framework v1.0

Implemented:

- Configuration
- REST Assured
- Base API Client
- DTO (Java Record)
- Allure Report
- Request/Response Logging
- Docker
- Docker CI
- Authentication Client
- First API Test