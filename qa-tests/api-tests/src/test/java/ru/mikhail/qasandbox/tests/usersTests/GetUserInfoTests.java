package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetUserInfoTests extends AuthenticatedTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        ApiResponse<CreateUsersResponse> response = usersClient.createUser(TestUsers.user());
        idToRemove = response.body().id();
    }

    @Test
    void shouldGetUserInfo() {
        Allure.step("Get user info");

        ApiResponse<GetUserResponse> response =
                usersClient.getUserById(idToRemove);

        assertThat(response.statusCode()).isEqualTo(200);

        assertThat(response.body().id()).isEqualTo(idToRemove);
        assertThat(response.body().name()).isEqualTo(TestUsers.user().name());
        assertThat(response.body().email()).isEqualTo(TestUsers.user().email());
        assertThat(response.body().role()).isEqualTo(TestUsers.user().role());
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            usersClient.deleteUser(idToRemove);
        }
    }
}