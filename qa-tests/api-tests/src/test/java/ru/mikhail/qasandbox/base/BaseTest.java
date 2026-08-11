package ru.mikhail.qasandbox.base;

import org.junit.jupiter.api.BeforeEach;
import ru.mikhail.qasandbox.client.AuthClient;
import ru.mikhail.qasandbox.client.UsersClient;
import ru.mikhail.qasandbox.config.Config;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.db.DbClient;

public abstract class BaseTest {

    protected AuthClient authClient;
    protected UsersClient usersClient;
    protected DbClient dbClient;
    protected String token;

    @BeforeEach
    void setUp() {
        authClient = new AuthClient();
        usersClient = new UsersClient();

        dbClient = new DbClient(
                Config.getDbUrl(),
                Config.getDbUser(),
                Config.getDbPassword()
        );

        token = authClient.login(TestUsers.admin()).token();

        usersClient.setToken(token);
    }
}