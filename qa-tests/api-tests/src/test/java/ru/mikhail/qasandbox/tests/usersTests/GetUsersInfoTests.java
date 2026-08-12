package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.config.UserDataConfig;
import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetUsersInfoTests extends AuthenticatedTest {

    @Test
    void shouldGetUsersInfo() {
        Allure.step("Get users info");

        ApiResponse<GetUsersResponse[]> response =
                usersClient.getUsersResponse();

        assertThat(response.statusCode()).isEqualTo(200);

        assertThat(response.body()).isNotEmpty();

        assertThat(response.body())
                .anySatisfy(user -> {
                    assertThat(user.id()).isNotNull().isPositive();
                    assertThat(user.email()).isEqualTo(UserDataConfig.getAdminEmail());
                    assertThat(user.name()).isEqualTo(UserDataConfig.ADMIN_NAME);
                    assertThat(user.role()).isEqualTo(UserDataConfig.getAdminRole());
                });
    }
}