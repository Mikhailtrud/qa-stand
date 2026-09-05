package tests.authTests;

import framework.auth.StorageAuthProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

@Epic("QA Stand Mobile")
@Feature("Authentication")
@Story("Login")
class StorageAuthTest extends BaseTest {
    private final StorageAuthProvider authProvider = new StorageAuthProvider();

    @Test
    @Description("Administrator is authorized through the application's SharedPreferences storage")
    void adminCanAuthorizeThroughStorage() {
        authProvider.authorizeAsAdmin();
        usersSteps.verifyUsersScreenDisplayed();
    }
}
