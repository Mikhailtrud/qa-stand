package config;

import java.nio.file.Path;

public final class AppiumConfig {
    private static final Path DEFAULT_APK = Path.of(
            System.getProperty("mobile.tests.projectDir", System.getProperty("user.dir")),
            "..", "..", "android-app", "app", "build", "outputs", "apk", "debug", "app-debug.apk"
    ).normalize().toAbsolutePath();

    private AppiumConfig() {
    }

    public static String serverUrl() {
        return ConfigValue.get("APPIUM_URL", "http://127.0.0.1:4723");
    }

    public static String deviceName() {
        return ConfigValue.get("DEVICE_NAME", "Android Emulator");
    }

    public static String udid() {
        return ConfigValue.get("UDID", "");
    }

    public static String platformVersion() {
        return ConfigValue.get("PLATFORM_VERSION", "");
    }

    public static Path appPath() {
        return Path.of(ConfigValue.get("APP_PATH", DEFAULT_APK.toString())).normalize().toAbsolutePath();
    }

    public static String appPackage() {
        return ConfigValue.get("APP_PACKAGE", "com.qastand.android");
    }

    public static String appActivity() {
        return ConfigValue.get("APP_ACTIVITY", ".MainActivity");
    }

    public static Path adbPath() {
        String configured = ConfigValue.get("ADB_PATH", "");
        if (!configured.isBlank()) {
            return Path.of(configured).normalize().toAbsolutePath();
        }

        String androidSdk = ConfigValue.get(
                "ANDROID_SDK_ROOT",
                ConfigValue.get("ANDROID_HOME", "")
        );
        if (androidSdk.isBlank()) {
            return Path.of("adb");
        }
        return Path.of(androidSdk, "platform-tools", isWindows() ? "adb.exe" : "adb")
                .normalize()
                .toAbsolutePath();
    }

    public static long explicitWaitSeconds() {
        return Long.parseLong(ConfigValue.get("EXPLICIT_WAIT_SECONDS", "10"));
    }

    public static long implicitWaitSeconds() {
        return Long.parseLong(ConfigValue.get("IMPLICIT_WAIT_SECONDS", "2"));
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
