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
@Story("Mouse Actions")
public class MouseActionsTest extends BaseTest {
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
    @Description("Tapping Hover displays the Hover result")
    void tapDisplaysHoverResult() {
        playgroundSteps
                .tapHoverAction()
                .verifyMouseActionResult("Hover");
    }

    @Test
    @Description("Double tapping displays the Double Click result")
    void doubleTapDisplaysDoubleClickResult() {
        playgroundSteps
                .doubleClickAction()
                .verifyMouseActionResult("Double Click");
    }

    @Test
    @Description("Long pressing displays the Right Click result")
    void longPressDisplaysRightClickResult() {
        playgroundSteps
                .rightClickAction()
                .verifyMouseActionResult("Right Click");
    }
}
