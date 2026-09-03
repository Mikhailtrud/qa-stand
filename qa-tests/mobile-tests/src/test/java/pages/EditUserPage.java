package pages;

import org.openqa.selenium.By;

public final class EditUserPage extends BasePage {
    private final By screen = tag("edit_user_screen");
    private final By name = tag("user_name");
    private final By email = tag("user_email");
    private final By role = tag("user_role");
    private final By saveButton = tag("save_user_button");
    private final By cancelButton = tag("cancel_edit_user_button");

    public boolean isDisplayed() {
        return isDisplayed(screen);
    }

    public EditUserPage setName(String value) {
        type(name, value);
        return this;
    }

    public EditUserPage setEmail(String value) {
        type(email, value);
        return this;
    }

    public EditUserPage setRole(String value) {
        type(role, value);
        return this;
    }

    public void save() {
        clickable(saveButton).click();
    }

    public void cancelEdit() {
        clickable(cancelButton).click();
    }

    public boolean isSaveButtonClickable() {
        return Boolean.parseBoolean(
                visible(saveButton).getAttribute("clickable")
        );
    }
}
