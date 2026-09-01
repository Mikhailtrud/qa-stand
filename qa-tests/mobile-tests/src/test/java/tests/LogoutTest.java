package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

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
