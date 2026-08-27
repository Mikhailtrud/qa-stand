package tests.authTests;

import config.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.testData.LoginData;

@Epic("QA Stand")
@Feature("Authentication")
public class LoginTest extends BaseTest {

    @BeforeEach
    void setUp() {
        loginSteps.openLoginPage().verifyLoginLabelVisible();
    }

    @Test
    @Story("Successful login")
    void loginSuccessful() {
        loginSteps
                .login(LoginData.LOGIN_EMAIL, LoginData.LOGIN_PASSWORD)
                .qaSandboxLabelVisible();

        loginSteps
                .logout()
                .verifyLoginLabelVisible();
    }

    @Test
    @Story("Required password")
    void loginWithEmptyPassword() {
        loginSteps
                .login(LoginData.LOGIN_EMAIL , LoginData.EMPTY_LOGIN_PASSWORD)
                .loginErrorMessageVisible("Password is required");
    }

    @Test
    @Story("Required email")
    void loginWithEmptyEmail() {
        loginSteps
                .login(LoginData.EMPTY_LOGIN_EMAIL, LoginData.LOGIN_PASSWORD)
                .loginErrorMessageVisible("Email is required");
    }

    @Test
    @Story("Invalid credentials")
    void loginWithInvalidCredentials() {
        loginSteps
                .login(LoginData.INVALID_LOGIN_EMAIL, LoginData.INVALID_LOGIN_PASSWORD)
                .invalidCredentialsMessageVisible();
    }

}
