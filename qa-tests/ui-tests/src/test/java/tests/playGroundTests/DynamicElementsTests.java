package tests.playGroundTests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Epic("QA Stand")
@Feature("Playground: Dynamic")
public class DynamicElementsTests extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundDynamicMouseSteps
                .openPlayGroundPage()
                .verifyPlaygroundDynamicElementsVisible();
    }

    @Test
    @Story("Loader")
    void checkLoader() {
        playGroundDynamicMouseSteps
                .clickOnStartLoaderButton()
                .verifyLoaderVisible()
                .verifyLoaderText("Loading...");
    }

    @Test
    @Story("Delayed button")
    void checkShowDelayedButton() {
        playGroundDynamicMouseSteps
                .clickOnShowDelayedButton()
                .verifyPlaygroundDelayedButtonVisible()
                .verifyDelayedButtonText("Delayed Button");
    }

    @Test
    @Story("Hidden element")
    void checkHiddenElementAppears() {
        playGroundDynamicMouseSteps
                .verifyHiddenElementVisible()
                .verifyDynamicsElementsBlockText("Hidden element appeared.");
    }

}
