package config;

import org.junit.jupiter.api.BeforeAll;
import steps.UserSteps;

public class BaseTest {

    protected final UserSteps userSteps = new UserSteps();

    @BeforeAll
    static void setup() {

        DriverConfig.configure();

    }

}