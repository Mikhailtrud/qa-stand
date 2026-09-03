package pages;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;

public final class LoginPage extends BasePage {
    private final By email = tag("login_email");
    private final By password = tag("login_password");
    private final By loginButton = tag("login_button");
    private final By errorMessage = AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter email and password\")");

    public LoginPage enterEmail(String value) {
        type(email, value);
        return this;
    }

    public LoginPage enterPassword(String value) {
        type(password, value);
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public void tapLogin() {
        tapAfterHidingKeyboard("login_button");
    }

    public boolean isDisplayed() {
        return isDisplayed(loginButton);
    }
}
