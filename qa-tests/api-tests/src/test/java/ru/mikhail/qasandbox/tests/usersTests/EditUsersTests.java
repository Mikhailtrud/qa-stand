package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.EditUserBuilder;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.data.testData.EditUserTestData;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mikhail.qasandbox.assertions.ApiResponseAssert.assertThat;

public class EditUsersTests extends AuthenticatedTest {

    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        CreateUsersRequest request = UserBuilder.validUser().build();
        ApiResponse<CreateUsersResponse> response = usersClient.createUser(request);

        assertThat(response)
                .hasStatusCode(201);

        idToRemove = response.body().id();
    }

    @Test
    void userShouldBeEdited() {
        EditUserRequest request = EditUserBuilder.validUser().build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit a user successfully",
                () -> usersClient.editUser(idToRemove, request)
        );

        assertThat(response)
                .hasStatusCode(200);

        EditUserResponse body = response.body();

        assertThat(body.id()).isEqualTo(idToRemove);
        assertThat(body.name()).isEqualTo(request.name());
        assertThat(body.email()).isEqualTo(request.email());
        assertThat(body.role()).isEqualTo(request.role());
    }

    @Test
    void userShouldNotBeEditedWithEmptyName() {
        EditUserRequest request = EditUserBuilder.validUser()
                .withName(EditUserTestData.EMPTY)
                .build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit a user with an empty name",
                () -> usersClient.editUser(idToRemove, request)
        );

        assertThat(response)
                .hasStatusCode(400)
                .hasErrorMessage("Validation failed");
    }

    @Test
    void userShouldNotBeEditedWithInvalidEmail() {
        EditUserRequest request = EditUserBuilder.validUser()
                .withEmail(EditUserTestData.INVALID_EMAIL)
                .build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit a user with an invalid email format",
                () -> usersClient.editUser(idToRemove, request)
        );

        assertThat(response)
                .hasStatusCode(400)
                .hasErrorMessage("Validation failed");
    }

    @Test
    void userShouldNotBeEditedWithInvalidRole() {
        EditUserRequest request = EditUserBuilder.validUser()
                .withRole(EditUserTestData.INVALID_ROLE)
                .build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit a user with an invalid role",
                () -> usersClient.editUser(idToRemove, request)
        );

        assertThat(response)
                .hasStatusCode(400)
                .hasErrorMessage("Validation failed");
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            ApiResponse<Void> response =
                    usersClient.deleteUser(idToRemove);

            assertThat(response)
                    .hasStatusCode(200);
        }
    }
}
