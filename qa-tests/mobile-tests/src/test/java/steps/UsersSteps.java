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

    @Step("Verify user with name {name} is displayed")
    public UsersSteps verifyUserDisplayedByName(String name) {
        assertThat(page.isUserDisplayedName(name))
                .as("User with name %s should be displayed", name)
                .isTrue();
        return this;
    }

    @Step("Verify user with email {email} is displayed")
    public UsersSteps verifyUserDisplayedByEmail(String email) {
        assertThat(page.isUserDisplayed(email))
                .as("User with email %s should be displayed", email)
                .isTrue();
        return this;
    }

    @Step("Verify user with email {email} is displayed")
    public UsersSteps verifyUserDisplayedByRole(String role) {
        assertThat(page.isUserDisplayedRole(role))
                .as("User with role %s should be displayed", role)
                .isTrue();
        return this;
    }

    @Step("Verify user with email {email} is not displayed")
    public UsersSteps verifyUserNotDisplayedByEmail(String email) {
        assertThat(page.isUserDisplayed(email))
                .as("User with email %s should be displayed", email)
                .isFalse();
        return this;
    }

    @Step("Delete user with id {userId}")
    public UsersSteps deleteUser(long userId) {
        page.deleteUser(userId);
        page.confirmDelete();
        return this;
    }

    @Step("Cancel delete user with id {userId}")
    public UsersSteps cancelDeleteUser(long userId) {
        page.deleteUser(userId);
        page.cancelDelete();
        return this;
    }

    @Step("Open edit form for user with id {userId}")
    public UsersSteps editUser(long userId) {
        page.editUser(userId);
        return this;
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
