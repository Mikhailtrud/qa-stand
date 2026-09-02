package tests.authTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

@Epic("QA Stand Mobile")
@Feature("Login")
class LoginTest extends BaseTest {

    @Test
    @Description("A valid administrator can log in and reach the Users screen")
    void adminCanLogin() {
        loginSteps.loginAsAdmin();
        usersSteps.verifyUsersScreenDisplayed();
    }

    @Test
    @Description("Error message should be visible after login with empty email")
    void loginWithEmptyEmail() {
        loginSteps
                .login("", "123123123")
                .verifyErrorMessageShouldBeVisible();
    }

    @Test
    @Description("Error message should be visible after login with empty password")
    void loginWithEmptyPassword() {
        loginSteps
                .login("testuser@email.com", "")
                .verifyErrorMessageShouldBeVisible();
    }

    @Test
    @Description("Error message should be visible after login with empty email and password")
    void loginWithEmptyEmailAndPassword() {
        loginSteps
                .login("", "")
                .verifyErrorMessageShouldBeVisible();
    }
}
