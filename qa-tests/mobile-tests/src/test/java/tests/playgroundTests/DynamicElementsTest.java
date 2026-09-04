package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class DynamicElementsTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        playgroundSteps
                .openPlayground()
                .verifyPlaygroundDisplayed()
                .scrollDawn();
    }

    @Test
    @Description("")
    void checkLoader() {
        playgroundSteps
                .startLoader()
                .verifyLoaderDisplayed();
    }

    @Test
    @Description("")
    void checkShowDelayedButton() {
        playgroundSteps
                .showDelayedButton()
                .delayedButtonVisible();
    }

    @Test
    @Description("")
    void checkHiddenElementAppears() {
        playgroundSteps
                .verifyHiddenElementText("Hidden element appeared.");
    }

}
