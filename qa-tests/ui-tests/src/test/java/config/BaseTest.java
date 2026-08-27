package config;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import steps.*;
import steps.playground.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    protected final LoginSteps loginSteps = new LoginSteps();
    protected final UserSteps userSteps = new UserSteps();
    protected final PlayGroundFormsSteps playGroundFormsSteps = new PlayGroundFormsSteps();
    protected final PlayGroundJavaScriptSteps playGroundJavaScriptSteps = new PlayGroundJavaScriptSteps();
    protected final PlayGroundTabsSteps playGroundTabsSteps = new PlayGroundTabsSteps();
    protected final PlayGroundTablesSteps playGroundTablesSteps = new PlayGroundTablesSteps();
    protected final PlayGroundDynamicMouseSteps playGroundDynamicMouseSteps = new PlayGroundDynamicMouseSteps();
    protected final PlayGroundMouseActionsSteps playGroundMouseActionsSteps = new PlayGroundMouseActionsSteps();

    @BeforeAll
    static void setup() {

        DriverConfig.configure();
        SelenideLogger.addListener(
                "allure",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
