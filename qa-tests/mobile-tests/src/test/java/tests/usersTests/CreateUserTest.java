package tests.usersTests;

import data.builder.UserBuilder;
import framework.api.UsersApiClient;
import framework.auth.IntentAuthProvider;
import data.testData.UserData;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.BaseTest;

import java.util.stream.Stream;

import static data.testData.UserTestData.*;

@Epic("QA Stand Mobile")
@Feature("Users")
@Story("Create User")
public class CreateUserTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();
    private final UsersApiClient usersApiClient = new UsersApiClient();
    private String createdUserEmail;

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        usersSteps
                .verifyUsersScreenDisplayed()
                .openCreateUser();
    }

    @Test
    @Description("An administrator can create a unique user through the mobile UI")
    void administratorCanCreateUser() {
        UserData user = UserBuilder.user().build();
        createdUserEmail = user.email();

        createUserSteps
                .fillForm(user)
                .verifySaveButtonEnabled()
                .save();

        usersSteps.verifyUserDisplayedByEmail(user.email());
    }

    @ParameterizedTest(name = "{index}: expected error = {1}")
    @MethodSource("emptyRequiredFields")
    @Description("An empty required field displays validation and keeps Save disabled")
    void shouldShowValidationErrorForEmptyRequiredField(
            UserData user,
            String expectedError
    ) {
        createUserSteps
                .fillForm(user)
                .verifyValidationError(expectedError)
                .verifySaveButtonNotClickable();
    }

    static Stream<Arguments> emptyRequiredFields() {
        return Stream.of(
                Arguments.of(
                        UserBuilder.user().withName(EMPTY).build(),
                        "Name is required"
                ),
                Arguments.of(
                        UserBuilder.user().withEmail(EMPTY).build(),
                        "Email is required"
                ),
                Arguments.of(
                        UserBuilder.user().withPassword(EMPTY).build(),
                        "Password is required"
                ),
                Arguments.of(
                        UserBuilder.user().withRole(EMPTY).build(),
                        "Role is required"
                )
        );
    }

    @ParameterizedTest(name = "{index}: expected error = {1}")
    @MethodSource("invalidRequiredFields")
    @Description("Invalid user data displays validation and keeps Save disabled")
    void shouldShowValidationErrorForInvalidRequiredField(
            UserData user,
            String expectedError
    ) {
        createUserSteps
                .fillForm(user)
                .verifyValidationError(expectedError)
                .verifySaveButtonNotClickable();
    }

    static Stream<Arguments> invalidRequiredFields() {
        return Stream.of(
                Arguments.of(
                        UserBuilder.user().withName(INVALID_NAME).build(),
                        "Name is required"
                ),
                Arguments.of(
                        UserBuilder.user().withEmail(INVALID_EMAIL).build(),
                        "Enter a valid email"
                ),
                Arguments.of(
                        UserBuilder.user().withPassword(INVALID_PASSWORD).build(),
                        "Use 8+ chars with a letter and number"
                ),
                Arguments.of(
                        UserBuilder.user().withRole(INVALID_ROLE).build(),
                        "Use USER or ADMIN"
                )
        );
    }

    @AfterEach
    void deleteCreatedUser() {
        if (createdUserEmail != null && !createdUserEmail.isBlank()) {
            usersApiClient.deleteByEmailIfExists(createdUserEmail);
        }
    }
}
