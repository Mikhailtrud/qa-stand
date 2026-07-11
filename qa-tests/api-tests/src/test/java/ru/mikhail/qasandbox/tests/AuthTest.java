package ru.mikhail.qasandbox.tests;

import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.config.Config;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.LoginResponse;

import static org.assertj.core.api.Assertions.assertThat;

class AuthTest extends BaseTest {

    @Test
    void shouldLoginSuccessfully() {

        LoginResponse response = authClient.login(TestUsers.admin());

        assertThat(response).isNotNull();
        assertThat(response.token()).isNotBlank();
        assertThat(response.role()).isEqualTo(Config.getAdminRole());
    }
}