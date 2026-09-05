package tests.usersTests;

import data.builder.UserBuilder;
import data.testData.UserData;
import framework.api.UsersApiClient;
import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import tests.BaseTest;

@Epic("QA Stand Mobile")
@Feature("Users")
@Story("Delete User")
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
    @Description("User should be deleted and should not be visible in users list")
    void userCanBeDeleted() {
        usersSteps
                .refreshUsers()
                .deleteUser(createdUserId)
                .verifyUserCardNotDisplayed(createdUserId);
        createdUserId = 0;
    }

    @Test
    @Description("User should not be deleted and should be displayed in users list")
    void userDeletionCanBeCancelled() {
        usersSteps
                .refreshUsers()
                .cancelDeleteUser(createdUserId)
                .verifyUserCard(createdUserId, user.name(), user.email(), user.role());
    }

    @AfterEach
    void deleteCreatedUser() {
        if (createdUserId != 0) {
            usersApiClient.deleteByID(createdUserId);
        }
    }

}
