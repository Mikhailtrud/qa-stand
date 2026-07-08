package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class UsersPage extends BasePage {

    private final SelenideElement emailInput =
            $("[data-testid='email-input']");

    private final SelenideElement nameInput =
            $("[data-testid='name-input']");

    private final SelenideElement roleSelect =
            $("[data-testid='role-select']");

    private final SelenideElement createUserButton =
            $("[data-testid='create-user-button']");

    private final SelenideElement usersTable =
            $("[data-testid='users-table']");

    public UsersPage open() {
        openPage("/");
        return this;
    }

    public UsersPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public UsersPage enterName(String name) {
        nameInput.setValue(name);
        return this;
    }

    public UsersPage selectRole(String role) {
        roleSelect.selectOption(role);
        return this;
    }

    public UsersPage clickCreateUser() {
        createUserButton.click();
        return this;
    }

    public SelenideElement usersTable() {
        return usersTable;
    }

}