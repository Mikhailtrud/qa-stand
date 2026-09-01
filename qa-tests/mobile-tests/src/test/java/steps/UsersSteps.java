package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import pages.UsersPage;

public final class UsersSteps extends BaseSteps {
    private final UsersPage page = new UsersPage();

    @Step("Verify the Users screen is displayed")
    public UsersSteps verifyUsersScreenDisplayed() {
        assertThat(page.isDisplayed())
                .as("Users screen should be visible after login")
                .isTrue();
        return this;
    }

    @Step("Refresh users")
    public UsersSteps refreshUsers() {
        page.refresh();
        return this;
    }

    @Step("Open the create user screen")
    public UsersSteps openCreateUser() {
        page.openCreateUser();
        return this;
    }

    @Step("Verify user with email {email} is displayed")
    public void verifyUserDisplayed(String email) {
        assertThat(page.isUserDisplayed(email))
                .as("User with email %s should be displayed", email)
                .isTrue();
    }

    @Step("Delete user {email} through the mobile UI")
    public void deleteUser(long userId, String email) {
        page.scrollToUser(email);
        page.deleteUser(userId);
        page.confirmDelete();
    }

    @Step("Verify the users list is displayed")
    public void verifyUsersListDisplayed() {
        assertThat(page.isUsersListDisplayed()).isTrue();
    }

    @Step("Log out")
    public void logout() {
        page.logout();
    }
}
