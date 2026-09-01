package tests;

import data.builder.UserBuilder;
import data.testData.UserData;
import framework.api.UsersApiClient;
import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteUserTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();
    private final UsersApiClient usersApiClient = new UsersApiClient();
    private long createdUserId;
    private String createdUserEmail;
    private UserData user = UserBuilder.user().build();

    @BeforeEach
    void setUp() {
        UserData user = UserBuilder.user().build();
        authProvider.authorizeAsAdmin();
        createdUserId = usersApiClient.createUser(user);
        createdUserEmail = user.email();
        usersSteps.verifyUsersScreenDisplayed();
    }

    @Test
    @Description("")
    void deleteUserTest(){
        System.out.println("id = " + createdUserId + " email = " +  user.email());
        usersSteps
                .refreshUsers()
                .deleteUser(createdUserId);
        createdUserEmail = "";
    }

    @AfterEach
    void deleteCreatedUser() {
        if (createdUserEmail != null && !createdUserEmail.isBlank()) {
            usersApiClient.deleteByEmailIfExists(createdUserEmail);
        }
    }

}
