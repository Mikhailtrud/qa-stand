package ru.mikhail.qasandbox.base;

import org.junit.jupiter.api.BeforeEach;
import ru.mikhail.qasandbox.client.AuthClient;
import ru.mikhail.qasandbox.client.UsersClient;
import ru.mikhail.qasandbox.config.DbConfig;
import ru.mikhail.qasandbox.db.DbClient;

public abstract class BaseTest {

    protected AuthClient authClient;
    protected UsersClient usersClient;
    protected DbClient dbClient;

    @BeforeEach
    void setUp() {
        authClient = new AuthClient();
        usersClient = new UsersClient();

        dbClient = new DbClient(
                DbConfig.getUrl(),
                DbConfig.getUser(),
                DbConfig.getPassword()
        );
    }
}