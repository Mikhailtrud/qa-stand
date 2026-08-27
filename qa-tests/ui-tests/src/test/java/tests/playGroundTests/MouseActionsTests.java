package tests.playGroundTests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Epic("QA Stand")
@Feature("Playground: Mouse Actions")
public class MouseActionsTests  extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundMouseActionsSteps
                .openPlayGroundPage()
                .verifyPlaygroundMouseActionsVisible();
    }

    @Test
    @Story("Hover")
    void hoverTest() {
        playGroundMouseActionsSteps
                .hoverElement()
                .verifyMouseActionResult("Hover");
    }

    @Test
    @Story("Double click")
    void doubleClickTest() {
        playGroundMouseActionsSteps
                .doubleClickElement()
                .verifyMouseActionResult("Double Click");
    }

    @Test
    @Story("Right click")
    void rightClickTest() {
        playGroundMouseActionsSteps
                .rightClickElement()
                .verifyMouseActionResult("Right Click");
    }


}
