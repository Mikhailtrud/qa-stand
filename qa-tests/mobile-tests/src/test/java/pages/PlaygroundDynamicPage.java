package pages;

import io.appium.java_client.Setting;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebElement;

public final class PlaygroundDynamicPage extends BasePage {
    private final By playgroundScreen = tag("playground_screen");
    private final By startLoaderButton = tag("start_loader_button");
    private final By showDelayedButton = tag("show_delayed_button");
    private final By delayedButton = tag("delayed_button");
    private final By loader = tag("loader");
    private final By hiddenElementText = tag("hidden_element");
    private final By hoverButton = tag("hover_button");
    private boolean loaderAppeared;

    public void scrollToBottom() {
        swipeUpToElement(hoverButton);
    }

    public void startLoader() {
        Object previousIdleTimeout = getDriver().getSettings().get("waitForIdleTimeout");
        loaderAppeared = false;

        getDriver().setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 0);
        try {
            RemoteWebElement button = (RemoteWebElement) visible(startLoaderButton);
            getDriver().executeScript(
                    "mobile: clickGesture",
                    Map.of("elementId", button.getId())
            );
            loaderAppeared = present(loader) != null;
        } catch (NoSuchElementException | TimeoutException ignored) {
            loaderAppeared = false;
        } finally {
            if (previousIdleTimeout != null) {
                getDriver().setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, previousIdleTimeout);
            }
        }
    }

    public void showDelayedButton() {
        clickable(showDelayedButton).click();
    }

    public boolean isDelayedButtonDisplayed() {
        return isDisplayed(delayedButton);
    }

    public boolean isLoaderDisplayed() {
        return loaderAppeared;
    }

    public String getHiddenElementText() {
        return visible(hiddenElementText).getText();
    }

    public boolean isDisplayed() {
        return isDisplayed(playgroundScreen);
    }
}
