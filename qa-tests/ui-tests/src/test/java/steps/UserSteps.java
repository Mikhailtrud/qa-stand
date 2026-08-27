package steps;

import com.codeborne.selenide.Condition;
import data.testData.UserData;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class UserSteps extends BaseSteps {

    @Step("Open the users page")
    public UserSteps openUsersPage() {
        usersPage.open();
        return this;
    }

    @Step("Open the users tab")
    public UserSteps openUsersTab() {
        usersPage.openUsersTab();
        return this;
    }

    @Step("Create user {email} with name {name} and role {role}")
    public UserSteps createUser(String email, String password, String name, String role) {
        usersPage.enterEmail(email);
        usersPage.enterPassword(password);
        usersPage.enterName(name);
        usersPage.selectRole(role);
        usersPage.clickCreateUserButton();
        return this;
    }

    @Step("Fill user form for {email} with name {name} and role {role}")
    public UserSteps createUserWithEmptyField(String email, String password, String name, String role) {
        usersPage.enterEmail(email);
        usersPage.enterPassword(password);
        usersPage.enterName(name);
        usersPage.selectRole(role);
        return this;
    }

    @Step("Create user from test data")
    public UserSteps createUser(UserData user) {
        return createUser(user.email(), user.password(), user.name(), user.role());
    }

    @Step("Fill user form from test data")
    public UserSteps createUserWithEmptyField(UserData user) {
        return createUserWithEmptyField(user.email(), user.password(), user.name(), user.role());
    }

    @Step("Enter user email: {email}")
    public UserSteps enterEmail(String email) {
        usersPage.enterEmail(email);
        return this;
    }

    @Step("Enter user password")
    public UserSteps enterPassword(String password) {
        usersPage.enterPassword(password);
        return this;
    }

    @Step("Enter user name: {name}")
    public UserSteps enterName(String name) {
        usersPage.enterName(name);
        return this;
    }

    @Step("Select user role: {role}")
    public UserSteps selectRole(String role) {
        usersPage.selectRole(role);
        return this;
    }

    @Step("Submit user creation")
    public UserSteps clickCreateUser() {
        usersPage.clickCreateUserButton();
        return this;
    }

    @Step("Verify that the create-user button is not clickable")
    public UserSteps verifyCreateButtonShouldNotBeClickable() {
        usersPage.createUserButton().shouldNotBe(clickable);
        return this;
    }

    @Step("Verify that the users table is visible")
    public UserSteps verifyUsersTableVisible() {
        usersPage.usersTable().shouldBe(Condition.visible);
        return this;
    }

    @Step("Verify that user {email} exists")
    public UserSteps verifyUserExists(String email) {
        usersPage.userRowByEmail(email).shouldBe(Condition.visible);
        return this;
    }

    @Step("Verify that user {email} does not exist")
    public UserSteps verifyUserNotExists(String email) {
        usersPage.userRowByEmail(email).shouldNot(exist);
        return this;
    }

    @Step("Verify that the row for user ID {id} is visible")
    public UserSteps verifyUserRowVisible(long id) {
        usersPage.userRowById(id).shouldBe(visible);
        return this;
    }

    @Step("Verify email {email} in the row for user ID {id}")
    public UserSteps verifyUserRowEmail(long id, String email) {
        usersPage.userRowById(id).shouldHave(text(email));
        return this;
    }

    @Step("Verify the user creation validation message")
    public UserSteps loginErrorMessageVisible() {
        usersPage.createUserValidationErrorMessage().should(visible).shouldHave(text("Invalid email"));
        return this;
    }

    @Step("Verify the successful user deletion message")
    public UserSteps deleteUserSuccessMessage() {
        usersPage.deleteUserSuccessMessage().should(visible).shouldHave(text("User deleted successfully."));
        return this;
    }

    @Step("Delete user with ID {id}")
    public UserSteps deleteUser(long id) {
        usersPage.userDeleteButton(id).click();
        return this;
    }

}
