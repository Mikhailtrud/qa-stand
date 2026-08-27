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

Raw Allure results are written to:

```text
../reports/ui/allure-results
```

Generate a fresh retained report after the test run:

```powershell
.\gradlew.bat allureReport --clean
```

The generated report is written to:

```text
../reports/ui/allure-report
```

Open it with the Allure command line downloaded by the Gradle plugin:

```powershell
.\build\allure\commandline\bin\allure.bat open ..\reports\ui\allure-report
```

Runtime system properties:

| Property | Default | Use |
|---|---|---|
| `baseUrl` | `http://localhost:5173` | frontend URL |
| `browser` | `chrome` | Selenide browser |
| `remote` | `false` | enables remote WebDriver execution |
| `remoteUrl` | `http://localhost:4444/wd/hub` | Selenium Grid/Selenoid URL used when `remote=true` |

Example override:

```powershell
.\gradlew.bat test -DbaseUrl=http://localhost:5173 -Dbrowser=chrome
```

`DriverConfig` applies the base URL, browser, optional remote URL, `1920x1080`
window, eager page-load strategy, and a 10-second timeout. The Gradle build uses
a Java 21 toolchain.

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

UI Allure raw results and the retained static report are stored under
`qa-tests/reports/ui`, outside the Gradle build directory.
