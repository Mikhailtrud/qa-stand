package tests;

import config.AuthenticatedTest;
import data.testData.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static data.builder.UserBuilder.user;

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
                .verifyUsersTableVisible();

        $("[data-testid='user-row-" + createdUserId + "']")
                .shouldHave(text(createdUser.email()));
    }

    @AfterEach
    void deleteUserViaApi() {
        if (createdUserId != 0) {
            apiHelper.deleteUser(authToken, createdUserId);
        }
    }
}
