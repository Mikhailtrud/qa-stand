package tests.usersTests;

import data.builder.UserBuilder;
import data.testData.UserData;
import framework.api.UsersApiClient;
import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import tests.BaseTest;

import static data.testData.UserTestData.*;

public class EditUsersTest extends BaseTest {

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
    void editUserNameTest() {
        usersSteps
                .refreshUsers()
                .editUser(createdUserId);

        editUserSteps
                .editName(EDIT_NAME)
                .save();

        usersSteps.verifyUserDisplayedByName(EDIT_NAME);
    }

    @Test
    @Description("")
    void editUserEmailTest() {
        usersSteps
                .refreshUsers()
                .editUser(createdUserId);

        editUserSteps
                .editEmail(EDIT_EMAIL)
                .save();

        usersSteps.verifyUserDisplayedByEmail(EDIT_EMAIL);
    }

    @Test
    @Description("")
    void editUserRoleTest() {
        usersSteps
                .refreshUsers()
                .editUser(createdUserId);

        editUserSteps
                .editRole(EDIT_ROLE)
                .save();

        usersSteps.verifyUserDisplayedByRole(EDIT_ROLE);
    }

    @Test
    @Description("")
    void editUserFieldsTest() {
        usersSteps
                .refreshUsers()
                .editUser(createdUserId);

        editUserSteps
                .replaceForm(EDIT_NAME, EDIT_EMAIL,EDIT_ROLE)
                .save();

        usersSteps
                .verifyUserDisplayedByName(EDIT_NAME)
                .verifyUserDisplayedByEmail(EDIT_EMAIL)
                .verifyUserDisplayedByRole(EDIT_ROLE);
    }

    @Test
    @Description("Edit should be cancelled and data should not be saved")
    void cancelEditUserFieldsTest() {
        usersSteps
                .refreshUsers()
                .editUser(createdUserId);

        editUserSteps
                .replaceForm(EDIT_NAME, EDIT_EMAIL,EDIT_ROLE)
                .cancelEdit();

        usersSteps
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
