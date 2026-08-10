package ru.mikhail.qasandbox.base;

import org.junit.jupiter.api.BeforeEach;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.LoginResponse;

public abstract class AuthenticatedTest extends BaseTest {

    protected String token;

    @BeforeEach
    void authenticate() {
        LoginResponse response = authClient.login(TestUsers.admin());
        token = response.token();
    }
}