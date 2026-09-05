package pages;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;

public final class PlaygroundMouseActionsPage extends BasePage {
    private final By playgroundScreen = tag("playground_screen");
    private final By hoverButton = tag("hover_button");
    private final By doubleClickButton = tag("double_click_button");
    private final By rightClickButton = tag("right_click_button");
    private final By mouseActionResult = tag("mouse_action_result");

    public void scrollToBottom() {
        swipeUpToElement(hoverButton);
    }

    public void tapHoverButton() {
        clickable(hoverButton).click();
    }

    public void doubleTapButton() {
        RemoteWebElement button = (RemoteWebElement) clickable(doubleClickButton);
        getDriver().executeScript("mobile: doubleClickGesture", Map.of("elementId", button.getId()));
    }

    public void longPressButton() {
        RemoteWebElement button = (RemoteWebElement) clickable(rightClickButton);
        getDriver().executeScript(
                "mobile: longClickGesture",
                Map.of("elementId", button.getId(), "duration", 1000)
        );
    }

    public String getMouseActionResult() {
        return visible(mouseActionResult).getText();
    }

    public boolean isDisplayed() {
        return isDisplayed(playgroundScreen);
    }
}
