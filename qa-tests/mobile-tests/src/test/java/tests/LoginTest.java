package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import steps.LoginSteps;

@Epic("QA Stand Mobile")
@Feature("Login")
class LoginTest extends BaseTest {
    @Test
    @Description("A valid administrator can log in and reach the Users screen")
    void adminCanLogin() {
        LoginSteps loginSteps = new LoginSteps(driver);

        loginSteps.loginAsAdmin();
        loginSteps.verifyUsersScreenDisplayed();
    }
}
