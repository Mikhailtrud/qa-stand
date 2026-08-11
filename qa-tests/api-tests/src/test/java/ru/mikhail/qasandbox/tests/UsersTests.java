package ru.mikhail.qasandbox.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersTests extends BaseTest {

    @Test
    void shouldGetUserInfo() {
        Allure.step("Get user info");

        GetUsersResponse[] response =
                usersClient.getUsersResponse();

        assertThat(response)
                .isNotNull()
                .isNotEmpty();

        assertThat(response)
                .anySatisfy(user -> {
                    assertThat(user.email()).isEqualTo("test@email.com");
                    assertThat(user.role()).isEqualTo("ADMIN");
                });
    }
}