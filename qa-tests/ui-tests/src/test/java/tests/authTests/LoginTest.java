package tests.authTests;

import config.BaseTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.testData.LoginData;

public class LoginTest extends BaseTest {

    @BeforeEach
    void sutUp() {
        loginSteps.openLoginPage().verifyLoginLabelVisible();
    }

    @Story("")
    @Test
    void loginSuccessful() {
        loginSteps
                .login(LoginData.LOGIN_EMAIL, LoginData.LOGIN_PASSWORD)
                .qaSandboxLabelVisible();

        loginSteps
                .logout()
                .verifyLoginLabelVisible();
    }

    @Story("")
    @Test
    void loginWithEmptyPassword() {
        loginSteps
                .login(LoginData.LOGIN_EMAIL , LoginData.EMPTY_LOGIN_PASSWORD)
                .loginErrorMessageVisible();
    }

    @Story("")
    @Test
    void loginWithEmptyEmail() {
        loginSteps
                .login(LoginData.EMPTY_LOGIN_EMAIL, LoginData.LOGIN_PASSWORD)
                .loginErrorMessageVisible();
    }

    @Story("")
    @Test
    void loginWithInvalidCredentials() {
        loginSteps
                .login(LoginData.INVALID_LOGIN_EMAIL, LoginData.INVALID_LOGIN_PASSWORD)
                .invalidCredentialsMessageVisible();
    }

}