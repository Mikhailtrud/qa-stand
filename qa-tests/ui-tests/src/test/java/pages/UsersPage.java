package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class UsersPage extends BasePage {

    private final SelenideElement emailInput =
            $("[data-testid='email-input']");

    private final SelenideElement passwordInput =
            $("[data-testid='password-input']");

    private final SelenideElement nameInput =
            $("[data-testid='name-input']");

    private final SelenideElement roleSelect =
            $("[data-testid='role-select']");

    public final SelenideElement createUserButton =
            $("[data-testid='create-user-button']");

    private final SelenideElement usersTable =
            $("[data-testid='users-table']");

    private final SelenideElement usersTab =
            $(".layout-body a[href='/users']");

    private final SelenideElement CheckUserTable =
            $(".users-table tr:nth-child(2)");

    private final SelenideElement createUserValidationErrorMessage =
            $(".alert.alert-error");

    private final SelenideElement deleteUserSuccessMessage =
            $(".alert.alert-success");




    public UsersPage open() {
        openPage("/users");
        return this;
    }

    public UsersPage enterPassword(String password) {
        passwordInput.setValue(password);
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

    public UsersPage clickCreateUserButton() {
        createUserButton.click();
        return this;
    }

    public UsersPage openUsersTab() {
        usersTab.click();
        return this;
    }

    public SelenideElement usersTable() {
        return usersTable;
    }

    public SelenideElement createdUsersTable() {
        return CheckUserTable;
    }

    public SelenideElement createUserValidationErrorMessage() {
        return createUserValidationErrorMessage;
    }

    public SelenideElement userRowByEmail(String email) {
        return usersTable.$$("tbody tr").findBy(text(email));
    }

    public SelenideElement userDeleteButton(long id) {
        return $("[data-testid='user-row-" + id + "'] .form-button");
    }

    public SelenideElement deleteUserSuccessMessage() {
        return deleteUserSuccessMessage;
    }

}
