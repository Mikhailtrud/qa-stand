package steps;

import framework.config.EnvironmentConfig;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public final class LoginSteps extends BaseSteps {

    @Step("Log in as the configured administrator")
    public void loginAsAdmin() {
        login(EnvironmentConfig.adminEmail(), EnvironmentConfig.adminPassword());
    }

    @Step("Log in with email {email}")
    public LoginSteps login(String email, String password) {
        loginPage.enterEmail(email)
                .enterPassword(password)
                .tapLogin();
        return this;
    }

    @Step("Error message should be visible")
    public LoginSteps verifyErrorMessageDisplayed() {
        assertThat(loginPage.isErrorMessageDisplayed()).isTrue();
        return this;
    }

    @Step("Verify the Login screen is displayed")
    public void verifyLoginScreenDisplayed() {
        assertThat(loginPage.isDisplayed())
                .as("Login screen should be visible")
                .isTrue();
    }

}
