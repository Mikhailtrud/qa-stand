package tests.playGroundTests;

import config.AuthenticatedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MouseActionsTests  extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundMouseActionsSteps
                .openPlayGroundPage()
                .verifyPlaygroundMouseActionsVisible();
    }

    @Test
    void hoverTest() {
        playGroundMouseActionsSteps
                .hoverElement()
                .verifyMouseActionResult("Hover");

        // проверка результата hover
    }

    @Test
    void doubleClickTest() {
        playGroundMouseActionsSteps
                .doubleClickElement()
                .verifyMouseActionResult("Double Click");

        // проверка результата double click
    }

    @Test
    void rightClickTest() {
        playGroundMouseActionsSteps
                .rightClickElement()
                .verifyMouseActionResult("Right Click ");

        // проверка результата right click
    }


}
