package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;

public final class UsersPage extends BasePage {
    private final By screen = tag("users_screen");
    private final By usersList = tag("users_list");
    private final By refreshButton = tag("refresh_users_button");
    private final By createButton = tag("create_user_button");
    private final By playgroundButton = tag("open_playground_button");
    private final By logoutButton = tag("logout_button");
    private final By cancelDeleteUserButton = tag("cancel_delete_user_button");

    private By userCard(long id) {
        return tag("user_card_" + id);
    }

    public boolean isDisplayed() {
        return isDisplayed(screen);
    }

    public boolean isUsersListDisplayed() {
        return isDisplayed(usersList);
    }

    public boolean isUserDisplayed(String email) {
        return isDisplayed(userByEmail(email));
    }

    private By userByEmail(String email) {
        String escapedEmail = email
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");

        return AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"" + escapedEmail + "\")"
        );
    }

    public List<String> getUserCardValues(long id) {
        WebElement card = scrollToElement(usersList, userCard(id));
        return card.findElements(By.className("android.widget.TextView"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isUserCardDisplayed(long id) {
        try {
            return scrollToElement(usersList, userCard(id)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException ignored) {
            return false;
        }
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

    public void confirmDelete() {
        clickable(tag("confirm_delete_button")).click();
    }

    public void cancelDelete() {
        clickable(tag("cancel_delete_user_button")).click();
    }

    public void deleteUser(long id) {
        By deleteButton = tag("delete_user_button_" + id);

        scrollToElement(usersList, deleteButton).click();
    }

    public void editUser(long id) {
        scrollToElement(usersList, tag("edit_user_" + id)).click();
    }

}
