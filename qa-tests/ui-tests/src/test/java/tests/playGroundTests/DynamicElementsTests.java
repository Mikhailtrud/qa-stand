package tests.playGroundTests;

import config.AuthenticatedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamicElementsTests extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundDynamicMouseSteps.openPlayGroundPage().verifyPlaygroundDynamicElementsVisible().verifyDynamicsElementsBlockText("Hidden element appeared.");
    }

    @Test
    void checkLoader() {
        playGroundDynamicMouseSteps
                .clickOnStartLoaderButton()
                .verifyPlaygroundDynamicLoaderVisible()
                .verifyPLoaderText("Loading...");
    }

    @Test
    void checkShowDelayedButton() {
        playGroundDynamicMouseSteps
                .clickOnShowDelayedButton()
                .verifyPlaygroundDelayedButtonVisible()
                .verifyDelayedButtonText("Delayed Button");
    }

}
