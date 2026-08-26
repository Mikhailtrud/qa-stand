package config;

import java.nio.file.Path;

public final class TestConfig {
    private static final Path DEFAULT_APK = Path.of(
            System.getProperty("mobile.tests.projectDir", System.getProperty("user.dir")),
            "..", "..", "android-app", "app", "build", "outputs", "apk", "debug", "app-debug.apk"
    ).normalize().toAbsolutePath();

    private TestConfig() {
    }

    public static String appiumUrl() {
        return value("APPIUM_URL", "http://127.0.0.1:4723");
    }

    public static String deviceName() {
        return value("DEVICE_NAME", "Android Emulator");
    }

    public static String platformVersion() {
        return value("PLATFORM_VERSION", "");
    }

    public static Path appPath() {
        return Path.of(value("APP_PATH", DEFAULT_APK.toString())).normalize().toAbsolutePath();
    }

    public static String backendUrl() {
        return value("BACKEND_URL", "http://localhost:8080");
    }

    public static String adminEmail() {
        return requiredValue("ADMIN_EMAIL");
    }

    public static String adminPassword() {
        return requiredValue("ADMIN_PASSWORD");
    }

    public static long explicitWaitSeconds() {
        return Long.parseLong(value("EXPLICIT_WAIT_SECONDS", "10"));
    }

    public static long implicitWaitSeconds() {
        return Long.parseLong(value("IMPLICIT_WAIT_SECONDS", "2"));
    }

    private static String value(String name, String defaultValue) {
        String systemValue = System.getProperty(name);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        String environmentValue = System.getenv(name);
        return environmentValue == null || environmentValue.isBlank() ? defaultValue : environmentValue;
    }

    private static String requiredValue(String name) {
        String configured = value(name, "");
        if (configured.isBlank()) {
            throw new IllegalStateException(name + " must be provided as an environment variable or system property");
        }
        return configured;
    }
}
