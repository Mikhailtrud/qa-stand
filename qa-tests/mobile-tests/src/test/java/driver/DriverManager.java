package driver;

import io.appium.java_client.android.AndroidDriver;

public final class DriverManager {
    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static AndroidDriver start() {
        AndroidDriver driver = AndroidDriverFactory.create();
        DRIVER.set(driver);
        return driver;
    }

    public static AndroidDriver get() {
        AndroidDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("AndroidDriver has not been started");
        }
        return driver;
    }

    public static void quit() {
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
