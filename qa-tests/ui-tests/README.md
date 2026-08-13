# UI tests

JUnit 5 and Selenide UI tests for QA Sandbox.

## Current scope

The current `LoginTest` opens the users page and verifies that the users table
is visible. The project uses page objects, step classes, and shared assertions.

## Run

The frontend must be available at `http://localhost:5173` by default. From this
directory on Windows:

```powershell
.\gradlew.bat test
```

Runtime system properties:

| Property | Default | Use |
|---|---|---|
| `baseUrl` | `http://localhost:5173` | frontend URL |
| `browser` | `chrome` | Selenide browser |
| `remote` | `false` | stored by `TestConfig`; not currently applied by `DriverConfig` |
| `remoteUrl` | `http://localhost:4444/wd/hub` | stored by `TestConfig`; not currently applied by `DriverConfig` |

Example override:

```powershell
.\gradlew.bat test -DbaseUrl=http://localhost:5173 -Dbrowser=chrome
```

`DriverConfig` currently applies the base URL, browser, `1920x1080` window,
eager page-load strategy, and a 10-second timeout.

## Structure

```text
src/test/java/
├── assertions/  # shared UI assertions
├── config/      # Selenide and runtime configuration
├── elements/    # base element abstraction
├── pages/       # page objects
├── steps/       # user-facing UI actions
└── tests/       # JUnit scenarios
```

UI Allure raw results are configured under `build/allure-results`. The retained
static-report workflow documented at the qa-tests root currently applies to
the API project only.
