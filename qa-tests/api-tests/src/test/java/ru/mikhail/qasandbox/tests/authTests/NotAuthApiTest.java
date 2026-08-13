package ru.mikhail.qasandbox.tests.authTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.EditUserBuilder;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.*;

import static org.assertj.core.api.Assertions.assertThat;

public class NotAuthApiTest extends BaseTest {

    @Test
    void shouldNotBeLoggedCreateUser() {
        CreateUsersRequest request = UserBuilder.validUser().build();
        ApiResponse<CreateUsersResponse> response = Allure.step(
                "Not authorized create user test",
                () -> usersClient.createUser(request)
        );

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void shouldNotBeLoggedEditUser() {
        EditUserRequest request = EditUserBuilder.validUser().build();

        ApiResponse<EditUserResponse> response = Allure.step(
                "Not authorized edit user test",
                () -> usersClient.editUser(1, request)
        );

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void shouldNotBeLoggedDeleteUser() {
        ApiResponse<Void> response = Allure.step(
                "Not authorized delete user test",
                () -> usersClient.deleteUser(1)
        );

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void shouldNotBeLoggedGetUserInfo() {
        ApiResponse<GetUserResponse> response = Allure.step(
                "Not authorized get user test",
                () -> usersClient.getUserById(1)
        );

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void shouldNotBeLoggedGetUsersInfo() {
        ApiResponse<GetUsersResponse[]> response = Allure.step(
                "Not authorized get users info test",
                () -> usersClient.getUsersResponse()
        );

        assertThat(response.statusCode()).isEqualTo(401);
    }
}
