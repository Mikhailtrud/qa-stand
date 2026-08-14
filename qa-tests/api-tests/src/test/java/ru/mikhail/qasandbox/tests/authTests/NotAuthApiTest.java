package ru.mikhail.qasandbox.tests.authTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.EditUserBuilder;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

import static ru.mikhail.qasandbox.assertions.ApiResponseAssert.assertThat;

public class NotAuthApiTest extends BaseTest {

    @Test
    void shouldNotBeLoggedCreateUser() {
        CreateUsersRequest request = UserBuilder.validUser().build();

        ApiResponse<CreateUsersResponse> response = Allure.step(
                "Create a user without authorization",
                () -> usersClient.createUser(request)
        );

        assertThat(response)
                .hasStatusCode(401);
    }

    @Test
    void shouldNotBeLoggedEditUser() {
        EditUserRequest request = EditUserBuilder.validUser().build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Edit a user without authorization",
                () -> usersClient.editUser(1, request)
        );

        assertThat(response)
                .hasStatusCode(401);
    }

    @Test
    void shouldNotBeLoggedDeleteUser() {
        ApiResponse<Void> response = Allure.step(
                "Delete a user without authorization",
                () -> usersClient.deleteUser(1)
        );

        assertThat(response)
                .hasStatusCode(401);
    }

    @Test
    void shouldNotBeLoggedGetUserInfo() {
        ApiResponse<GetUserResponse> response = Allure.step(
                "Get a user without authorization",
                () -> usersClient.getUserById(1)
        );

        assertThat(response)
                .hasStatusCode(401);
    }

    @Test
    void shouldNotBeLoggedGetUsersInfo() {
        ApiResponse<GetUsersResponse[]> response = Allure.step(
                "Get users without authorization",
                () -> usersClient.getUsersResponse()
        );

        assertThat(response)
                .hasStatusCode(401);
    }
}
