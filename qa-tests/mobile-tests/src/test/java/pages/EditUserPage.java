package pages;

import org.openqa.selenium.By;

public final class EditUserPage extends BasePage {
    private final By screen = tag("edit_user_screen");
    private final By name = tag("user_name");
    private final By email = tag("user_email");
    private final By role = tag("user_role");
    private final By saveButton = tag("save_user_button");

    public boolean isDisplayed() {
        return isDisplayed(screen);
    }

    public EditUserPage replaceName(String value) {
        type("user_name", value);
        return this;
    }

    public EditUserPage replaceEmail(String value) {
        type("user_email", value);
        return this;
    }

    public EditUserPage replaceRole(String value) {
        type("user_role", value);
        return this;
    }

    public void save() {
        clickable(saveButton).click();
    }
}
