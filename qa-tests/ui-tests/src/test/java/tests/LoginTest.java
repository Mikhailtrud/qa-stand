package tests;

import config.BaseTest;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    void openUsersPage() {

        userSteps
                .openUsersPage()
                .verifyUsersTableVisible();

    }

}