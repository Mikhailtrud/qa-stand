package ru.mikhail.qasandbox.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.config.UserDataConfig;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetUsersInfoTests extends BaseTest {

    @BeforeEach
    void authorize() {
        String token = authClient.login(TestUsers.admin()).token();
        usersClient.setToken(token);
    }

    @Test
    void shouldGetUsersInfo() {
        Allure.step("Get user info");

        GetUsersResponse[] response =
                usersClient.getUsersResponse();

        assertThat(response)
                .isNotNull()
                .isNotEmpty();

        assertThat(response)
                .anySatisfy(user -> {
                    assertThat(user.id()).isNotNull().isPositive();
                    assertThat(user.email()).isEqualTo(UserDataConfig.getAdminEmail());
                    assertThat(user.name()).isEqualTo(UserDataConfig.ADMIN_NAME);
                    assertThat(user.role()).isEqualTo(UserDataConfig.getAdminRole());

                });
    }
}