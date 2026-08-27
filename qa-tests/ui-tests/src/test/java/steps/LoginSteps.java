package steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class LoginSteps extends BaseSteps {

    @Step("Open the login page")
    public LoginSteps openLoginPage() {
        loginPage.open();
        return this;
    }

    @Step("Log in as {email}")
    public LoginSteps login(String email, String password) {
        loginPage.fillLoginInput(email);
        loginPage.fillPasswordInput(password);
        loginPage.submitLogin();
        return this;
    }

    @Step("Verify that the Login title is visible")
    public LoginSteps verifyLoginLabelVisible() {
        loginPage.loginTitle().shouldBe(visible).shouldHave(text("Login"));
        return this;
    }

    @Step("Log out")
    public LoginSteps logout() {
        loginPage.logout();
        return this;
    }

    @Step("Verify login validation message: {text}")
    public LoginSteps loginErrorMessageVisible(String text) {
        loginPage.loginErrorMessage().should(visible).shouldHave(text(text));
        return this;
    }

    @Step("Verify that the QA Sandbox title is visible")
    public LoginSteps qaSandboxLabelVisible() {
        loginPage.appTitle().shouldBe(visible).shouldHave(text("QA Sandbox"));
        return this;
    }

    @Step("Verify the invalid credentials message")
    public LoginSteps invalidCredentialsMessageVisible() {
        loginPage.loginErrorMessage().should(visible).shouldHave(text("Invalid credentials"));
        return this;
    }

}
