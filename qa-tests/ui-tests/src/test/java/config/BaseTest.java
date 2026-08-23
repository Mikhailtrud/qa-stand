package config;

import org.junit.jupiter.api.BeforeAll;
import steps.*;

public class BaseTest {

    protected final LoginSteps loginSteps = new LoginSteps();
    protected final UserSteps userSteps = new UserSteps();
    protected final PlayGroundFormsSteps playGroundFormsSteps = new PlayGroundFormsSteps();
    protected final PlayGroundJavaScriptSteps playGroundJavaScriptSteps = new PlayGroundJavaScriptSteps();
    protected final PlayGroundTabsSteps playGroundTabsSteps = new PlayGroundTabsSteps();

    @BeforeAll
    static void setup() {

        DriverConfig.configure();

    }

}