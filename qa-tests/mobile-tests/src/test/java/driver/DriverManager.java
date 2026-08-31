package driver;

import io.appium.java_client.android.AndroidDriver;

public final class DriverManager {
    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setDriver(AndroidDriver driver) {
        if (DRIVER.get() != null) {
            throw new IllegalStateException("AndroidDriver is already set for the current thread");
        }
        DRIVER.set(driver);
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("AndroidDriver has not been started for the current thread");
        }
        return driver;
    }

    public static void quitDriver() {
        AndroidDriver driver = DRIVER.get();
        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }
}
