package tests.usersTests;

import config.AuthenticatedTest;
import data.testData.UserData;
import data.testData.UserTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static data.builder.UserBuilder.user;

public class CreateUserTest extends AuthenticatedTest {

    private UserData user;
    private boolean createdViaUi;

    @BeforeEach
    void setUp() {
        userSteps
                .openUsersTab()
                .verifyUsersTableVisible();
    }

    @Test
    void createUserSuccess() {
        user = user().build();

        userSteps
                .createUser(user)
                .verifyUserExists(user.email());

        createdViaUi = true;
    }

    @Test
    void createUserWithInvalidData() {
        user = user().withEmail(UserTestData.INVALID_EMAIL).build();

        userSteps
                .createUser(user)
                .loginErrorMessageVisible();
    }

    @Test
    void createUserWithEmptyEmail() {
        user = user().withEmail(UserTestData.EMPTY).build();

        userSteps
                .createUserWithEmptyField(user)
                .verifyCreateButtonShouldNotBeClickable();
    }

    @Test
    void createUserWithEmptyName() {
        user = user().withName(UserTestData.EMPTY).build();

        userSteps
                .createUserWithEmptyField(user)
                .verifyCreateButtonShouldNotBeClickable();
    }

    @Test
    void createUserWithEmptyPassword() {
        user = user().withPassword(UserTestData.EMPTY).build();

        userSteps
                .createUserWithEmptyField(user)
                .verifyCreateButtonShouldNotBeClickable();
    }

    @AfterEach
    void deleteUserViaApi() {
        if (createdViaUi) {
            long createdUserId = apiHelper.getUser(authToken, user.email());
            if (createdUserId != 0) {
                apiHelper.deleteUser(authToken, createdUserId);
            }
        }
    }

}
