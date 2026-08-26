package steps;

import com.codeborne.selenide.Condition;
import data.testData.UserData;

import static com.codeborne.selenide.Condition.*;

public class UserSteps extends BaseSteps {

    public UserSteps openUsersPage() {
        step("Открыть страницу пользователей");
        usersPage.open();
        return this;
    }

    public UserSteps openUsersTab() {
        step("Открыть страницу пользователей");
        usersPage.openUsersTab();
        return this;
    }

    public UserSteps createUser(String email, String password, String name, String role) {
        step("Создать пользователя");
        usersPage.enterEmail(email);
        usersPage.enterPassword(password);
        usersPage.enterName(name);
        usersPage.selectRole(role);
        usersPage.clickCreateUserButton();
        return this;
    }

    public UserSteps createUserWithEmptyField(String email, String password, String name, String role) {
        step("Создать пользователя");
        usersPage.enterEmail(email);
        usersPage.enterPassword(password);
        usersPage.enterName(name);
        usersPage.selectRole(role);
        return this;
    }

    public UserSteps createUser(UserData user) {
        return createUser(user.email(), user.password(), user.name(), user.role());
    }

    public UserSteps createUserWithEmptyField(UserData user) {
        return createUserWithEmptyField(user.email(), user.password(), user.name(), user.role());
    }

    public UserSteps enterEmail(String email) {
        step("Заполнить Email: " + email);
        usersPage.enterEmail(email);
        return this;
    }

    public UserSteps enterPassword(String pass) {
        step("Заполнить Password: " + pass);
        usersPage.enterPassword(pass);
        return this;
    }

    public UserSteps enterName(String name) {
        step("Заполнить Name: " + name);
        usersPage.enterName(name);
        return this;
    }

    public UserSteps selectRole(String role) {
        step("Выбрать роль: " + role);
        usersPage.selectRole(role);
        return this;
    }

    public UserSteps clickCreateUser() {
        step("Нажать Create User");
        usersPage.clickCreateUserButton();
        return this;
    }

    public UserSteps verifyCreateButtonShouldNotBeClickable() {
        step("Проверить кнопку создания пользователя");
        usersPage.createUserButton.shouldNotBe(clickable);
        return this;
    }

    public UserSteps verifyUsersTableVisible() {
        step("Проверить отображение таблицы пользователей");
        usersPage.usersTable().shouldBe(Condition.visible);
        return this;
    }

    public UserSteps verifyUserExists(String email) {
        step("Проверить пользователя с Email: " + email);
        usersPage.userRowByEmail(email).shouldBe(Condition.visible);
        return this;
    }

    public UserSteps verifyUserNotExists(String email) {
        step("Проверить пользователя с Email: " + email);
        usersPage.userRowByEmail(email).shouldNotBe(Condition.visible);
        return this;
    }

    public UserSteps loginErrorMessageVisible() {
        step("Проверить отображение ошибки Validation failed");
        usersPage.createUserValidationErrorMessage().should(visible).shouldHave(text("Validation failed"));
        return this;
    }

    public UserSteps deleteUserSuccessMessage() {
        step("Проверить отображение ошибки Validation failed");
        usersPage.deleteUserSuccessMessage().should(visible).shouldHave(text("User deleted successfully."));
        return this;
    }

    public UserSteps deleteUser(long id) {
        step("Проверить удаление пользователя");
        usersPage.userDeleteButton(id).click();
        return this;
    }

}
