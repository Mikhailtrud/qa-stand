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
        Allure.step("Not authorized create user test");

        CreateUsersRequest request = UserBuilder.validUser().build();
        ApiResponse<CreateUsersResponse> response =
                usersClient.createUser(request);

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void shouldNotBeLoggedEditUser() {
        Allure.step("Not authorized edit user test");

        EditUserRequest request = EditUserBuilder.validUser().build();

        ApiResponse<EditUserResponse> response =
                usersClient.editUser(1, request);

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void shouldNotBeLoggedDeleteUser() {
        Allure.step("Not authorized delete user test");


        ApiResponse<Void> response =
                usersClient.deleteUser(1);

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void shouldNotBeLoggedGetUserInfo() {
        Allure.step("Not authorized get user test");

        ApiResponse<GetUserResponse> response =
                usersClient.getUserById(1);

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void shouldNotBeLoggedGetUsersInfo() {
        Allure.step("Not authorized get users info test");

        ApiResponse<GetUsersResponse[]> response =
                usersClient.getUsersResponse();

        assertThat(response.statusCode()).isEqualTo(401);
    }
}