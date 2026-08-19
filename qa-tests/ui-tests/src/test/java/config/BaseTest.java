package config;

import org.junit.jupiter.api.BeforeAll;
import steps.LoginSteps;
import steps.UserSteps;

public class BaseTest {

    protected final LoginSteps loginSteps = new LoginSteps();
    protected final UserSteps userSteps = new UserSteps();

    @BeforeAll
    static void setup() {

        DriverConfig.configure();

    }

}