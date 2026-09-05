package tests.authTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

@Epic("QA Stand Mobile")
@Feature("Authentication")
@Story("Login")
class LogoutTest extends BaseTest {
    @Test
    @Description("Administrator can log out and return to the Login screen")
    void adminCanLogout() {
        loginSteps.loginAsAdmin();
        usersSteps.verifyUsersScreenDisplayed();

        usersSteps.logout();

        loginSteps.verifyLoginScreenDisplayed();
    }
}
