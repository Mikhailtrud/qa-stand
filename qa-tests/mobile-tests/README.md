# QA Stand mobile tests

Standalone Java 21/Appium foundation for native Android automation. The project currently contains one login smoke test; CRUD and Playground test suites are intentionally not implemented yet.

## Prerequisites

- JDK 21
- Gradle 8.x
- Android SDK and an Android emulator
- Node.js and Appium 2/3
- Appium UiAutomator2 driver
- QA Stand backend running locally or at `BACKEND_URL`
- Allure CLI to open reports

Install and verify Appium:

```powershell
npm install -g appium
appium driver install uiautomator2
appium driver doctor uiautomator2
```

## Build the application

From `android-app`:

```powershell
.\gradlew.bat assembleDebug
```

By default, the test project resolves the APK at:

```text
../../android-app/app/build/outputs/apk/debug/app-debug.apk
```

Override it with `APP_PATH` when needed. Appium installs/launches the configured APK, so a separate `adb install` is optional.

## Start the emulator and Appium

Start an Android Virtual Device, verify it with `adb devices`, then run:

```powershell
appium
```

The default device capability is `Android Emulator`. Set `DEVICE_NAME` to the device name expected by your local/CI Appium environment.

## Configuration

Environment variables (equivalent `-DNAME=value` system properties are also supported):

| Variable | Default |
|---|---|
| `APPIUM_URL` | `http://127.0.0.1:4723` |
| `DEVICE_NAME` | `Android Emulator` |
| `UDID` | unset |
| `PLATFORM_VERSION` | unset |
| `APP_PATH` | repository-relative debug APK |
| `APP_PACKAGE` | `com.qastand.android` |
| `APP_ACTIVITY` | `.MainActivity` |
| `ADB_PATH` | `$ANDROID_SDK_ROOT/platform-tools/adb` or `$ANDROID_HOME/platform-tools/adb` |
| `BACKEND_URL` | `http://localhost:8080` |
| `ADMIN_EMAIL` | required; no default |
| `ADMIN_PASSWORD` | required; no default |
| `EXPLICIT_WAIT_SECONDS` | `10` |
| `IMPLICIT_WAIT_SECONDS` | `2` |

Example:

```powershell
$env:DEVICE_NAME='emulator-5554'
$env:PLATFORM_VERSION='15'
$env:ADMIN_EMAIL='<admin-email>'
$env:ADMIN_PASSWORD='<admin-password>'
gradle test
```

## Run and report

From `qa-tests/mobile-tests`:

```powershell
gradle test
allure serve build/allure-results
```

For test discovery/compilation without starting Appium:

```powershell
gradle testClasses
```

The `BackendApiClient` is a deliberately small REST Assured helper for future API login, user creation, and cleanup preconditions.

## Technical authorization

`IntentAuthProvider` logs in through `/auth/login` and passes the returned token to the
debug-only `TestAuthActivity`. This activity is declared under `android-app/app/src/debug`,
so it is not present in release builds.

`StorageAuthProvider` logs in through the same API and writes the token to the application's
`auth_prefs` SharedPreferences with `adb run-as`. The installed application must be debuggable
for this variant. Both providers remove the stored authorization during test cleanup.
