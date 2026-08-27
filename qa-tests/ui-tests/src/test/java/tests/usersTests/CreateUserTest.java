package tests.usersTests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import data.testData.UserData;
import data.testData.UserTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static data.builder.UserBuilder.user;

@Epic("QA Stand")
@Feature("User Management")
public class CreateUserTest extends AuthenticatedTest {

    private UserData user;

    @BeforeEach
    void setUp() {
        userSteps
                .openUsersTab()
                .verifyUsersTableVisible();
    }

    @Test
    @Story("Create user")
    void createUserSuccess() {
        user = user().build();

        userSteps
                .createUser(user)
                .verifyUserExists(user.email());

    }

    @Test
    @Story("Invalid user data")
    void createUserWithInvalidData() {
        user = user().withEmail(UserTestData.INVALID_EMAIL).build();

        userSteps
                .createUser(user)
                .loginErrorMessageVisible();
    }

    @Test
    @Story("Required email")
    void createUserWithEmptyEmail() {
        user = user().withEmail(UserTestData.EMPTY).build();

        userSteps
                .createUserWithEmptyField(user)
                .verifyCreateButtonShouldNotBeClickable();
    }

    @Test
    @Story("Required name")
    void createUserWithEmptyName() {
        user = user().withName(UserTestData.EMPTY).build();

        userSteps
                .createUserWithEmptyField(user)
                .verifyCreateButtonShouldNotBeClickable();
    }

    @Test
    @Story("Required password")
    void createUserWithEmptyPassword() {
        user = user().withPassword(UserTestData.EMPTY).build();

        userSteps
                .createUserWithEmptyField(user)
                .verifyCreateButtonShouldNotBeClickable();
    }

    @AfterEach
    void deleteUserViaApi() {
        if (user != null) {
            apiHelper.findUserId(authToken, user.email())
                    .ifPresent(id -> apiHelper.deleteUserIfExists(authToken, id));
        }
    }

}
