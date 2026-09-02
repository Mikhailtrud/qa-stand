package tests.authTests;

import framework.auth.StorageAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

class StorageAuthTest extends BaseTest {
    private final StorageAuthProvider authProvider = new StorageAuthProvider();

    @Test
    @Description("Administrator is authorized through the application's SharedPreferences storage")
    void adminCanAuthorizeThroughStorage() {
        authProvider.authorizeAsAdmin();
        usersSteps.verifyUsersScreenDisplayed();
    }
}
