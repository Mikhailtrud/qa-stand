package ru.mikhail.qasandbox.base;

import org.junit.jupiter.api.BeforeEach;
import ru.mikhail.qasandbox.client.AuthClient;

public abstract class BaseTest {

    protected AuthClient authClient;

    @BeforeEach
    void setUp() {
        authClient = new AuthClient();
    }
}