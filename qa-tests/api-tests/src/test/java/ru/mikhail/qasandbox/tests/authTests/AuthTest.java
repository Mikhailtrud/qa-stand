package ru.mikhail.qasandbox.tests.authTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.client.AuthClient;
import ru.mikhail.qasandbox.config.UserDataConfig;
import ru.mikhail.qasandbox.data.testData.AdminTestData;
import ru.mikhail.qasandbox.dto.response.LoginResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mikhail.qasandbox.assertions.ApiResponseAssert.assertThat;

public class AuthTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    void shouldLoginSuccessfully() {
        ApiResponse<LoginResponse> response = Allure.step(
                "Login as admin",
                () -> authClient.login(AdminTestData.admin())
        );

        assertThat(response)
                .hasStatusCode(200);

        LoginResponse body = response.body();

        assertThat(body.token()).isNotBlank();
        assertThat(body.role()).isEqualTo(UserDataConfig.getAdminRole());
    }
}
