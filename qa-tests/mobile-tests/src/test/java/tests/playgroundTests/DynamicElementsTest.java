package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

@Epic("QA Stand Mobile")
@Feature("QA Playground")
@Story("Dynamic Elements")
public class DynamicElementsTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        playgroundSteps
                .openPlayground()
                .verifyPlaygroundDisplayed()
                .scrollToBottom();
    }

    @Test
    @Description("Starting the loader displays its progress indicator")
    void loaderAppearsAfterStart() {
        playgroundSteps
                .startLoader()
                .verifyLoaderDisplayed();
    }

    @Test
    @Description("The delayed button appears after it is requested")
    void delayedButtonAppears() {
        playgroundSteps
                .showDelayedButton()
                .verifyDelayedButtonDisplayed();
    }

    @Test
    @Description("The hidden element becomes visible with the expected text")
    void hiddenElementAppears() {
        playgroundSteps
                .verifyHiddenElementText("Hidden element appeared.");
    }

}
