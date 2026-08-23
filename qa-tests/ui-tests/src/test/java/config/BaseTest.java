package config;

import org.junit.jupiter.api.BeforeAll;
import steps.LoginSteps;
import steps.PlayGroundFormsSteps;
import steps.PlayGroundJavaScriptSteps;
import steps.UserSteps;

public class BaseTest {

    protected final LoginSteps loginSteps = new LoginSteps();
    protected final UserSteps userSteps = new UserSteps();
    protected final PlayGroundFormsSteps playGroundFormsSteps = new PlayGroundFormsSteps();
    protected final PlayGroundJavaScriptSteps playGroundJavaScriptSteps = new PlayGroundJavaScriptSteps();

    @BeforeAll
    static void setup() {

        DriverConfig.configure();

    }

}