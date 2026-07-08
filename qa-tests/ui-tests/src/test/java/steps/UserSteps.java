package steps;

import com.codeborne.selenide.Condition;

public class UserSteps extends BaseSteps {

    public UserSteps openUsersPage() {
        step("Открыть страницу пользователей");
        usersPage.open();
        return this;
    }

    public UserSteps enterEmail(String email) {
        step("Заполнить Email: " + email);
        usersPage.enterEmail(email);
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
        usersPage.clickCreateUser();
        return this;
    }

    public UserSteps verifyUsersTableVisible() {
        step("Проверить отображение таблицы пользователей");
        usersPage.usersTable().shouldBe(Condition.visible);
        return this;
    }

}