package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class CreateUserPage extends BasePage {
    private final By screen = tag("create_user_screen");
    private final By name = tag("user_name");
    private final By email = tag("user_email");
    private final By password = tag("user_password");
    private final By role = tag("user_role");
    private final By saveButton = tag("save_user_button");
    private final By errorMessageEmptyFields = AppiumBy.androidUIAutomator("new UiSelector().text(\"Fill in all required fields\")");
    private final By errorMessageInvalidEmail = AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter a valid email\")");


    public boolean isDisplayed() {
        return isDisplayed(screen);
    }

    public CreateUserPage enterName(String value) {
        type(name, value);
        return this;
    }

    public CreateUserPage enterEmail(String value) {
        type(email, value);
        return this;
    }

    public CreateUserPage enterPassword(String value) {
        type(password, value);
        return this;
    }

    public CreateUserPage enterRole(String value) {
        type(role, value);
        return this;
    }

    public void save() {
        tapAfterHidingKeyboard("save_user_button");
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessageInvalidEmail);
    }

    public boolean isSaveButtonEnabled() {
        return visible(saveButton).isEnabled();
    }

    public boolean isSaveButtonClickable() {
        return Boolean.parseBoolean(
                visible(saveButton).getAttribute("clickable")
        );
    }

    public boolean isValidationErrorDisplayed(String text) {
        return isDisplayed(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"" + text + "\")"
                )
        );
    }
}
