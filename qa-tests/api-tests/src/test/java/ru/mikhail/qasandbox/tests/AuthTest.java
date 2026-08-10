package ru.mikhail.qasandbox.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.client.AuthClient;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.LoginResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    void shouldLoginSuccessfully() {
        Allure.step("Login as admin");

        LoginResponse response = authClient.login(TestUsers.admin());

        assertThat(response).isNotNull();
        assertThat(response.token()).isNotBlank();
    }
}