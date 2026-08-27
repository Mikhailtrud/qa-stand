# QA Sandbox tests

Java autotests for QA Sandbox.

## Structure

```text
qa-tests/
├── api-tests/   # JUnit 5 and REST Assured API tests
├── ui-tests/    # JUnit 5 and Selenide UI tests
├── common/      # reserved for shared test code; currently empty
└── reports/     # generated reports kept outside Gradle build directories
```

The API and UI projects have separate Gradle wrappers and are run from their
own directories.

## API tests

The backend and PostgreSQL must be available before the tests start. From the
repository root they can be started with:

```powershell
docker compose up -d postgres backend
```

Run the API tests on Windows:

```powershell
cd qa-tests\api-tests
.\gradlew.bat test
```

JUnit Platform discovers the test classes under `src/test/java`; there is no
separate suite class in the current source tree.

Raw Allure data is written to `api-tests/build/allure-results`. Generate a
fresh static report separately:

```powershell
.\gradlew.bat allureReport --clean
```

The finished report is
`qa-tests/reports/api/allure-report/index.html`. Serve that directory with a
local HTTP server to view it in a browser; opening `index.html` via `file://`
may prevent the report from loading its JSON data. `allureServe` is not the
main storage or viewing workflow for this project.

See [api-tests/README.md](api-tests/README.md) for configuration, architecture,
logging, cleanup, and known issues.

## UI tests

The UI tests expect the frontend at `http://localhost:5173` by default. See
[ui-tests/README.md](ui-tests/README.md) for the current test and launch
options.

From `qa-tests/ui-tests`, run the tests and generate the retained Allure report:

```powershell
.\gradlew.bat test
.\gradlew.bat allureReport --clean
```

UI raw results and the generated report are stored at:

```text
qa-tests/reports/ui/allure-results
qa-tests/reports/ui/allure-report
```

## Docker CI note

`docker-compose.ci.yml` defines PostgreSQL, backend, frontend, and an API test
container. Its API test command still refers to the former root
`allure-results` copy workflow and does not generate the current static report.
Use the Gradle commands above for the documented report workflow.
