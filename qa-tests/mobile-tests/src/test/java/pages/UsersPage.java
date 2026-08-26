package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public final class UsersPage extends BasePage {
    private final By screen = tag("users_screen");
    private final By usersList = tag("users_list");
    private final By refreshButton = tag("refresh_users_button");
    private final By createButton = tag("create_user_button");
    private final By playgroundButton = tag("open_playground_button");

    public UsersPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isDisplayed(screen);
    }

    public boolean isUsersListDisplayed() {
        return isDisplayed(usersList);
    }

    public void refresh() {
        clickable(refreshButton).click();
    }

    public void openCreateUser() {
        clickable(createButton).click();
    }

    public void openPlayground() {
        clickable(playgroundButton).click();
    }

    public void editUser(long id) {
        clickable(tag("edit_user_" + id)).click();
    }

    public void deleteUser(long id) {
        clickable(tag("delete_user_button_" + id)).click();
    }

    public void confirmDelete() {
        clickable(tag("confirm_delete_button")).click();
    }
}
