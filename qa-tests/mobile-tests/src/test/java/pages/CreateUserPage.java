package pages;

import org.openqa.selenium.By;

public final class CreateUserPage extends BasePage {
    private final By screen = tag("create_user_screen");
    private final By name = tag("user_name");
    private final By email = tag("user_email");
    private final By password = tag("user_password");
    private final By role = tag("user_role");
    private final By saveButton = tag("save_user_button");

    public boolean isDisplayed() {
        return isDisplayed(screen);
    }

    public CreateUserPage enterName(String value) {
        type("user_name", value);
        return this;
    }

    public CreateUserPage enterEmail(String value) {
        type("user_email", value);
        return this;
    }

    public CreateUserPage enterPassword(String value) {
        type("user_password", value);
        return this;
    }

    public CreateUserPage enterRole(String value) {
        type("user_role", value);
        return this;
    }

    public void save() {
        clickable(saveButton).click();
    }
}
