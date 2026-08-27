package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    //Elements
    private final SelenideElement loginInput =
            $("[data-testid='login-email']");

    private final SelenideElement passwordInput =
            $("[data-testid='login-password']");

    private final SelenideElement loginButton =
            $("[data-testid='login-button']");

    private final SelenideElement loginTitle =
            $("[data-testid='login-title']");

    private final SelenideElement appTitle =
            $("[data-testid='app-title']");

    private final SelenideElement loginErrorMessage =
            $(".alert.alert-error");

    private final SelenideElement logoutButton =
            $("[data-testid='logout-button']");

    //Functions
    public LoginPage open() {
        openPage("/");
        return this;
    }

    public LoginPage fillLoginInput(String email) {
        loginInput.setValue(email);
        return this;
    }

    public LoginPage fillPasswordInput(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public LoginPage submitLogin() {
        loginButton.click();
        return this;
    }

    public LoginPage logout() {
        logoutButton.click();
        return this;
    }

    public SelenideElement loginTitle() {
        return loginTitle;
    }

    public SelenideElement appTitle() {
        return appTitle;
    }

    public SelenideElement loginErrorMessage() {
        return loginErrorMessage;
    }

}
