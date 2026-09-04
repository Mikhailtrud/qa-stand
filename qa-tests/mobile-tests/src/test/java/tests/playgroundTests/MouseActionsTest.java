package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class MouseActionsTest extends BaseTest {
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
    @Description("Tap Hover action")
    void hoverActionTest() {
        playgroundSteps
                .tapHoverAction()
                .verifyMouseActionResult("Hover");
    }

    @Test
    @Description("Double tap action")
    void doubleClickActionTest() {
        playgroundSteps
                .doubleClickAction()
                .verifyMouseActionResult("Double Click");
    }

    @Test
    @Description("Long press Right Click action")
    void rightClickActionTest() {
        playgroundSteps
                .rightClickAction()
                .verifyMouseActionResult("Right Click");
    }
}
