package tests;

import auth.StorageAuthProvider;
import config.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

class StorageAuthTest extends BaseTest {
    private final StorageAuthProvider authProvider = new StorageAuthProvider();

    @Test
    @Description("Administrator is authorized through the application's SharedPreferences storage")
    void adminCanAuthorizeThroughStorage() {
        authProvider.authorizeAsAdmin();
        usersSteps.verifyUsersScreenDisplayed();
    }
}
