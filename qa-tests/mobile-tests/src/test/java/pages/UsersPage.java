package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class UsersPage extends BasePage {
    private final By screen = tag("users_screen");
    private final By usersList = tag("users_list");
    private final By refreshButton = tag("refresh_users_button");
    private final By createButton = tag("create_user_button");
    private final By playgroundButton = tag("open_playground_button");
    private final By logoutButton = tag("logout_button");

    public boolean isDisplayed() {
        return isDisplayed(screen);
    }

    public boolean isUsersListDisplayed() {
        return isDisplayed(usersList);
    }

    public boolean isUserDisplayed(String email) {
        return isDisplayed(userByEmail(email));
    }

    public void scrollToUser(String email) {
        visible(userByEmail(email));
    }

    private By userByEmail(String email) {
        String escapedEmail = email.replace("\\", "\\\\").replace("\"", "\\\"");
        return AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))"
                        + ".scrollIntoView(new UiSelector().text(\"Email: " + escapedEmail + "\"))"
        );
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

    public void logout() {
        clickable(logoutButton).click();
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
