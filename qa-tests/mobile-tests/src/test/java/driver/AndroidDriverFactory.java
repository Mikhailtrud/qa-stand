package driver;

import config.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public final class AndroidDriverFactory {
    private AndroidDriverFactory() {
    }

    public static AndroidDriver create() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(TestConfig.deviceName())
                .setApp(TestConfig.appPath().toString())
                .setAppPackage("com.qastand.android")
                .setAppActivity(".MainActivity")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(120));

        if (!TestConfig.platformVersion().isBlank()) {
            options.setPlatformVersion(TestConfig.platformVersion());
        }

        try {
            AndroidDriver driver = new AndroidDriver(URI.create(TestConfig.appiumUrl()).toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestConfig.implicitWaitSeconds()));
            return driver;
        } catch (MalformedURLException error) {
            throw new IllegalArgumentException("Invalid APPIUM_URL: " + TestConfig.appiumUrl(), error);
        }
    }
}
