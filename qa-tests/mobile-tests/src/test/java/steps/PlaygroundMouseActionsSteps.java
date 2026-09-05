package steps;

import io.qameta.allure.Step;
import pages.PlaygroundMouseActionsPage;

import static org.assertj.core.api.Assertions.assertThat;

public final class PlaygroundMouseActionsSteps extends BaseSteps {
    private final PlaygroundMouseActionsPage playgroundMouseActionsPage = new PlaygroundMouseActionsPage();

    @Step("Verify QA Playground is displayed")
    public PlaygroundMouseActionsSteps verifyPlaygroundDisplayed() {
        assertThat(playgroundMouseActionsPage.isDisplayed()).isTrue();
        return this;
    }

    @Step("Scroll to the bottom of QA Playground")
    public PlaygroundMouseActionsSteps scrollToBottom() {
        playgroundMouseActionsPage.scrollToBottom();
        return this;
    }

    @Step("Tap Hover action")
    public PlaygroundMouseActionsSteps tapHoverAction() {
        playgroundMouseActionsPage.tapHoverButton();
        return this;
    }

    @Step("Double tap action")
    public PlaygroundMouseActionsSteps doubleClickAction() {
        playgroundMouseActionsPage.doubleTapButton();
        return this;
    }

    @Step("Long press Right Click action")
    public PlaygroundMouseActionsSteps rightClickAction() {
        playgroundMouseActionsPage.longPressButton();
        return this;
    }

    @Step("Verify mouse action result: {expectedText}")
    public PlaygroundMouseActionsSteps verifyMouseActionResult(String expectedText) {
        assertThat(playgroundMouseActionsPage.getMouseActionResult()).isEqualTo(expectedText);
        return this;
    }
}
