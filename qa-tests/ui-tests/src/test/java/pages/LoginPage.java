package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private final SelenideElement loginInput =
            $("[data-testid='login-email']");

    private final SelenideElement passwordInput =
            $("[data-testid='login-password']");

    public final SelenideElement loginButton =
            $("[data-testid='login-button']");

    public final SelenideElement loginLabel =
            $("#root h2");

    public final SelenideElement qaSandboxLabel =
            $("#root h2");

    public final SelenideElement loginErrorMessage =
            $("div[class=\"alert alert-error\"]");

    private final SelenideElement logoutButton =
            $("[data-testid='logout-button']");

    public LoginPage open() {
        openPage("/");
        return this;
    }

    public LoginPage fillLoginInput(String email) {
        loginInput.setValue(email);
        return this;
    }

    public LoginPage fillLPasswordInput(String email) {
        passwordInput.setValue(email);
        return this;
    }

    public LoginPage logout() {
        logoutButton.click();
        return this;
    }

}
