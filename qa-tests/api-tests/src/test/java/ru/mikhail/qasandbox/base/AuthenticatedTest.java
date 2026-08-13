package ru.mikhail.qasandbox.base;

import org.junit.jupiter.api.BeforeEach;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.testData.AdminTestData;
import ru.mikhail.qasandbox.dto.response.LoginResponse;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AuthenticatedTest extends BaseTest {

    protected String token;

    @BeforeEach
    void authenticate() {
        ApiResponse<LoginResponse> response =
                authClient.login(AdminTestData.admin());

        assertThat(response.statusCode()).isEqualTo(200);

        token = response.body().token();
        usersClient.setToken(token);
    }
}