package steps;

import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;

public abstract class BaseSteps {
    protected AndroidDriver getDriver() {
        return DriverManager.getDriver();
    }
}
