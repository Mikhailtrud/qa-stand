package ru.mikhail.qasandbox.tests.authTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.client.AuthClient;
import ru.mikhail.qasandbox.config.UserDataConfig;
import ru.mikhail.qasandbox.data.testData.AdminTestData;
import ru.mikhail.qasandbox.dto.response.LoginResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    void shouldLoginSuccessfully() {
        Allure.step("Login as admin");

        ApiResponse<LoginResponse> response =
                authClient.login(AdminTestData.admin());

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body().token()).isNotBlank();
        assertThat(response.body().role()).isEqualTo(UserDataConfig.getAdminRole());
    }
}