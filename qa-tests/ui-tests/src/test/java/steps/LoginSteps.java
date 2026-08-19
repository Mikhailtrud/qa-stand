package steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class LoginSteps extends BaseSteps {

    @Step
    public LoginSteps openLoginPage() {
        step("Открыть страницу авторизации");
        loginPage.open();
        return this;
    }

    @Step
    public LoginSteps login(String email, String password) {
        step("Залогиниться");
        loginPage.fillLoginInput(email);
        loginPage.fillLPasswordInput(password);
        loginPage.loginButton.click();;
        return this;
    }

    @Step
    public LoginSteps verifyLoginLabelVisible() {
        step("Проверить отображение лейбла Login");
        loginPage.loginLabel.shouldBe(visible);
        return this;
    }

    public LoginSteps logout() {
        step("Выйти из системы");
        loginPage.logout();
        return this;
    }

    @Step
    public LoginSteps loginErrorMessageVisible() {
        step("Проверить отображение лейбли QaSandbox");
        loginPage.loginErrorMessage.should(visible).shouldHave(text("Validation failed"));
        return this;
    }

    @Step
    public LoginSteps qaSandboxLabelVisible() {
        step("Проверить отображение лейбли QaSandbox");
        loginPage.qaSandboxLabel.shouldBe(visible).should(text("QA Sandbox"));
        return this;
    }

    @Step
    public LoginSteps invalidCredentialsMessageVisible() {
        step("Проверить отображение лейбли QaSandbox");
        loginPage.loginErrorMessage.should(visible).shouldHave(text("Invalid credentials"));
        return this;
    }

}
