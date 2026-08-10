package ru.mikhail.qasandbox.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.UsersClient;
import ru.mikhail.qasandbox.config.Config;
import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersTests extends AuthenticatedTest {

    @Test
    void shouldGetUserInfo() {
        Allure.step("Get user info");

        UsersClient usersClient = new UsersClient();

        GetUsersResponse[] response =
                usersClient.getUsersResponse(token);

        assertThat(response).isNotNull().isNotEmpty();
        assertThat(response)
                .anySatisfy(user -> {
                    assertThat(user.email()).isEqualTo("test@email.com");
                    assertThat(user.role()).isEqualTo("ADMIN");
                });
    }
}