package driver;

import config.AppiumConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public final class AndroidDriverProvider {
    public AndroidDriver create() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(AppiumConfig.deviceName())
                .setApp(AppiumConfig.appPath().toString())
                .setAppPackage(AppiumConfig.appPackage())
                .setAppActivity(AppiumConfig.appActivity())
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(120));

        if (!AppiumConfig.udid().isBlank()) {
            options.setUdid(AppiumConfig.udid());
        }
        if (!AppiumConfig.platformVersion().isBlank()) {
            options.setPlatformVersion(AppiumConfig.platformVersion());
        }

        try {
            AndroidDriver driver = new AndroidDriver(URI.create(AppiumConfig.serverUrl()).toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(AppiumConfig.implicitWaitSeconds()));
            return driver;
        } catch (MalformedURLException error) {
            throw new IllegalArgumentException("Invalid APPIUM_URL: " + AppiumConfig.serverUrl(), error);
        }
    }
}
