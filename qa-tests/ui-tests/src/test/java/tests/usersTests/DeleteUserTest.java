package tests.usersTests;

import config.AuthenticatedTest;
import data.testData.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static data.builder.UserBuilder.user;

public class DeleteUserTest extends AuthenticatedTest {

    private long createdUserId;
    private UserData createdUser;

    @BeforeEach
    void createUserViaApi() {
        createdUser = user().withName("User delete test").build();
        createdUserId = apiHelper.createUser(authToken, createdUser);
        userSteps.openUsersPage().verifyUsersTableVisible().verifyUserExists(createdUser.email());
    }

    @Test
    void deleteUserTest() {
        userSteps.deleteUser(createdUserId).verifyUserNotExists(createdUser.email()).deleteUserSuccessMessage();
    }
}
