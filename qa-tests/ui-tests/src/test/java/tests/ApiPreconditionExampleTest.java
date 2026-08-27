package tests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import data.testData.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static data.builder.UserBuilder.user;

@Epic("QA Stand")
@Feature("API Preconditions")
@Story("API-created user in UI")
public class ApiPreconditionExampleTest extends AuthenticatedTest {

    private long createdUserId;
    private UserData createdUser;

    @BeforeEach
    void createUserViaApi() {
        createdUser = user()
                .withName("User created for UI example")
                .build();

        createdUserId = apiHelper.createUser(authToken, createdUser);
    }

    @Test
    void userCreatedViaApiIsVisibleInUi() {
        userSteps.openUsersPage()
                .verifyUsersTableVisible()
                .verifyUserRowVisible(createdUserId)
                .verifyUserRowEmail(createdUserId, createdUser.email());
    }

    @AfterEach
    void deleteUserViaApi() {
        if (createdUserId != 0) {
            apiHelper.deleteUserIfExists(authToken, createdUserId);
        }
    }
}
