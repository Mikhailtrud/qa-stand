package tests.authTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

@Epic("QA Stand Mobile")
@Feature("Authentication")
@Story("Login")
class IntentAuthTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @Test
    @Description("Administrator is authorized through the debug-only Intent entry point")
    void adminCanAuthorizeThroughIntent() {
        authProvider.authorizeAsAdmin();
        usersSteps.verifyUsersScreenDisplayed();
    }
}
