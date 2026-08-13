package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.EditUserBuilder;
import ru.mikhail.qasandbox.data.testData.EditUserTestData;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class EditUsersTests extends AuthenticatedTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        ApiResponse<CreateUsersResponse> response =
                usersClient.createUser(UserBuilder.validUser().build());
        assertThat(response.statusCode()).isEqualTo(201);
        idToRemove = response.body().id();
    }

    @Test
    void userShouldBeEdited() {
        EditUserRequest request = EditUserBuilder.validUser().build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit user success",
                () -> usersClient.editUser(idToRemove, request)
        );

        assertThat(response.statusCode()).isEqualTo(200);

        assertThat(response.body().id()).isEqualTo(idToRemove);
        assertThat(response.body().name()).isEqualTo(request.name());
        assertThat(response.body().email()).isEqualTo(request.email());
        assertThat(response.body().role()).isEqualTo(request.role());
    }

    @Test
    void userShouldNotBeEditedWithEmptyName() {
        EditUserRequest request = EditUserBuilder.validUser()
                .withName(EditUserTestData.EMPTY)
                .build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit user with empty name",
                () -> usersClient.editUser(idToRemove, request)
        );

        assertThat(response.statusCode()).isEqualTo(422);
        assertThat(response.errorBody().message()).isEqualTo("Validation failed");
    }

    @Test
    void userShouldNotBeEditedWithInvalidEmail() {
        EditUserRequest request = EditUserBuilder.validUser()
                .withEmail(EditUserTestData.INVALID_EMAIL)
                .build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit user with invalid email format",
                () -> usersClient.editUser(idToRemove, request)
        );

        assertThat(response.statusCode()).isEqualTo(422);
        assertThat(response.errorBody().message()).isEqualTo("Validation failed");
    }

    @Test
    void userShouldNotBeEditedWithInvalidRole() {
        EditUserRequest request = EditUserBuilder.validUser()
                .withRole(EditUserTestData.INVALID_ROLE)
                .build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit user with invalid role",
                () -> usersClient.editUser(idToRemove, request)
        );

        assertThat(response.statusCode()).isEqualTo(422);
        assertThat(response.errorBody().message()).isEqualTo("Validation failed");
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            ApiResponse<Void> response =
                    usersClient.deleteUser(idToRemove);

            assertThat(response.statusCode()).isIn(200);
        }
    }

}
