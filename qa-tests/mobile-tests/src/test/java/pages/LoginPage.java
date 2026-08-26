package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public final class LoginPage extends BasePage {
    private final By email = tag("login_email");
    private final By password = tag("login_password");
    private final By loginButton = tag("login_button");

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String value) {
        type("login_email", value);
        return this;
    }

    public LoginPage enterPassword(String value) {
        type("login_password", value);
        return this;
    }

    public void tapLogin() {
        tapAfterHidingKeyboard("login_button");
    }

    public boolean isDisplayed() {
        return isDisplayed(loginButton);
    }
}
