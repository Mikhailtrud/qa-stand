package tests.usersTests;

import data.builder.UserBuilder;
import data.testData.UserData;
import framework.api.UsersApiClient;
import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import tests.BaseTest;

public class DeleteUserTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();
    private final UsersApiClient usersApiClient = new UsersApiClient();
    private long createdUserId;
    private final UserData user = UserBuilder.user().build();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        createdUserId = usersApiClient.createUser(user);
        usersSteps.verifyUsersScreenDisplayed();
    }

    @Test
    @Description("")
    void deleteUserTest(){
        usersSteps
                .refreshUsers()
                .deleteUser(createdUserId)
                .verifyUserNotDisplayedByEmail(user.email());
        createdUserId = 0;
    }

    @Test
    @Description("")
    void canselDeleteUserTest(){
        usersSteps
                .refreshUsers()
                .cancelDeleteUser(createdUserId)
                .verifyUserDisplayedByName(user.name())
                .verifyUserDisplayedByEmail(user.email())
                .verifyUserDisplayedByRole(user.role());
    }

    @AfterEach
    void deleteCreatedUser() {
        if (createdUserId != 0) {
            usersApiClient.deleteByID(createdUserId);
        }
    }

}
