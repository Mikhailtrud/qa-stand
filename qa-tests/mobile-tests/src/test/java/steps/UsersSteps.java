package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;

public final class UsersSteps extends BaseSteps {

    @Step("Verify the Users screen is displayed")
    public UsersSteps verifyUsersScreenDisplayed() {
        assertThat(usersPage.isDisplayed())
                .as("Users screen should be visible after login")
                .isTrue();
        return this;
    }

    @Step("Refresh users")
    public UsersSteps refreshUsers() {
        usersPage.refresh();
        return this;
    }

    @Step("Open the create user screen")
    public UsersSteps openCreateUser() {
        usersPage.openCreateUser();
        return this;
    }

    @Step("Verify user with email {email} is displayed")
    public UsersSteps verifyUserDisplayedByEmail(String email) {
        assertThat(usersPage.isUserDisplayed(email))
                .as("User with email %s should be displayed", email)
                .isTrue();
        return this;
    }

    @Step("Verify user card {userId}: {name}, {email}, {role}")
    public UsersSteps verifyUserCard(long userId, String name, String email, String role) {
        assertThat(usersPage.getUserCardValues(userId))
                .as("User card %s should contain the expected user data", userId)
                .contains(
                        "ID: " + userId,
                        name,
                        "Email: " + email,
                        "Role: " + role
                );
        return this;
    }

    @Step("Verify user card {userId} is not displayed")
    public UsersSteps verifyUserCardNotDisplayed(long userId) {
        assertThat(usersPage.isUserCardDisplayed(userId))
                .as("User card %s should not be displayed", userId)
                .isFalse();
        return this;
    }

    @Step("Delete user with id {userId}")
    public UsersSteps deleteUser(long userId) {
        usersPage.deleteUser(userId);
        usersPage.confirmDelete();
        return this;
    }

    @Step("Cancel delete user with id {userId}")
    public UsersSteps cancelDeleteUser(long userId) {
        usersPage.deleteUser(userId);
        usersPage.cancelDelete();
        return this;
    }

    @Step("Open edit form for user with id {userId}")
    public UsersSteps editUser(long userId) {
/*        usersPage.editUser(userId);*/
        usersPage.scrollToDawn(userId);
        usersPage.testElement(userId);
        return this;
    }

    @Step("Verify the users list is displayed")
    public void verifyUsersListDisplayed() {
        assertThat(usersPage.isUsersListDisplayed()).isTrue();
    }

    @Step("Log out")
    public void logout() {
        usersPage.logout();
    }
}
