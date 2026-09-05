# QA Stand mobile tests

Native Android UI tests for QA Stand.

## Stack

- Java 21
- JUnit 5
- Appium Java Client with UiAutomator2
- AssertJ
- Allure

## Structure

```text
src/test/java
├── framework   # driver, config, auth, API helpers, attachments
├── pages       # UI interaction and state
├── steps       # test actions and assertions
├── tests       # authentication, users and QA Playground tests
└── data        # test models, builders and fixed test data
```

The interaction flow is:

```text
Tests -> Steps -> Pages -> Appium / BasePage
```

Coverage includes Authentication, Users CRUD, Forms, Dialogs, Tabs, Tables,
Dynamic Elements and Mouse Actions.

## Locators

Use locators in this order:

1. `accessibilityId` / Android `content-desc`
2. `resource-id`
3. `UiSelector`
4. XPath only when a relationship cannot be expressed reliably otherwise

## Configuration

Tests require a running QA Stand backend, a local Appium server with the
UiAutomator2 driver, and a running Android emulator.

Administrator credentials are resolved in this order: explicit Gradle project
property, environment variable, QA Stand local default.

```powershell
.\gradlew.bat test -PADMIN_EMAIL='<admin-email>' -PADMIN_PASSWORD='<admin-password>'
# or
$env:ADMIN_EMAIL='<admin-email>'
$env:ADMIN_PASSWORD='<admin-password>'
```

Common optional properties and defaults:

| Property | Default |
|---|---|
| `APPIUM_URL` | `http://127.0.0.1:4723` |
| `DEVICE_NAME` | `Android Emulator` |
| `UDID` | empty |
| `PLATFORM_VERSION` | empty |
| `APP_PATH` | `../../android-app/app/build/outputs/apk/debug/app-debug.apk` |
| `APP_PACKAGE` | `com.qastand.android` |
| `APP_ACTIVITY` | `.MainActivity` |
| `BACKEND_URL` | `http://localhost:8080` |
| `ADB_PATH` | resolved from Android SDK, otherwise `adb` |
| `EXPLICIT_WAIT_SECONDS` | `10` |
| `IMPLICIT_WAIT_SECONDS` | `2` |

The local QA Stand defaults are `test@email.com` and `admin123`. CI credentials
should be supplied with `-PADMIN_EMAIL`/`-PADMIN_PASSWORD` or environment variables;
no credentials are stored in `gradle.properties`.

## Run

Build the debug APK first if `APP_PATH` does not point to an existing APK:

```powershell
cd ..\..\android-app
.\gradlew.bat assembleDebug
```

Start the emulator and Appium, then run from `qa-tests/mobile-tests`:

```powershell
.\gradlew.bat test
```

Compile tests without starting Appium:

```powershell
.\gradlew.bat compileTestJava
```

Allure results are written to `build/allure-results`. Generate and open a report with:

```powershell
allure serve build/allure-results
```
