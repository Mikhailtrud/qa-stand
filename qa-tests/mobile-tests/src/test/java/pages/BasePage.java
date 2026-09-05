package pages;

import framework.config.AppiumConfig;
import framework.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import org.openqa.selenium.remote.RemoteWebElement;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BasePage {
    private static final int MAX_SCROLL_ATTEMPTS = 20;

    protected AndroidDriver getDriver() {
        return DriverManager.getDriver();
    }

    private WebDriverWait explicitWait() {
        return new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(AppiumConfig.explicitWaitSeconds())
        );
    }

    protected By tag(String testTag) {
        return AppiumBy.accessibilityId(testTag);
    }

    protected WebElement visible(By locator) {
        return explicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement present(By locator) {
        return explicitWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement clickable(By locator) {
        return explicitWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void type(By locator, String value) {
        visible(locator).click();
        WebElement input = getDriver().switchTo().activeElement();
        input.clear();
        input.sendKeys(value);
    }

    protected void verifyText(By locator, String expectedText) {
        assertThat(visible(locator).getText())
                .isEqualTo(expectedText);
    }

    protected void tapAfterHidingKeyboard(String testTag) {
        try {
            getDriver().hideKeyboard();
        } catch (WebDriverException ignored) {
            // The keyboard may already be hidden.
        }
        clickable(tag(testTag)).click();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return visible(locator).isDisplayed();
        } catch (NoSuchElementException | TimeoutException ignored) {
            return false;
        }
    }

    protected void swipeUp() {
        Dimension windowSize = getDriver().manage().window().getSize();

        getDriver().executeScript(
                "mobile: swipeGesture",
                java.util.Map.of(
                        "left", windowSize.width / 10,
                        "top", windowSize.height / 5,
                        "width", windowSize.width * 8 / 10,
                        "height", windowSize.height * 6 / 10,
                        "direction", "up",
                        "percent", 0.75
                )
        );
    }

    protected WebElement swipeUpToElement(By targetLocator) {
        if (!getDriver().findElements(targetLocator).isEmpty()) {
            return visible(targetLocator);
        }

        for (int attempt = 1; attempt <= MAX_SCROLL_ATTEMPTS; attempt++) {
            swipeUp();

            if (!getDriver().findElements(targetLocator).isEmpty()) {
                return visible(targetLocator);
            }
        }

        throw new NoSuchElementException(
                "Element " + targetLocator
                        + " was not found after " + MAX_SCROLL_ATTEMPTS
                        + " upward swipe attempts"
        );
    }

    private boolean scrollDown(RemoteWebElement container) {
        return Boolean.TRUE.equals(
                DriverManager.getDriver().executeScript(
                        "mobile: scrollGesture",
                        Map.of(
                                "elementId", container.getId(),
                                "direction", "down",
                                "percent", 0.8
                        )
                )
        );
    }

    protected WebElement scrollToElement(By containerLocator, By targetLocator) {
        RemoteWebElement container =
                (RemoteWebElement) visible(containerLocator);

        if (!getDriver().findElements(targetLocator).isEmpty()) {
            return visible(targetLocator);
        }

        for (int attempt = 1; attempt <= MAX_SCROLL_ATTEMPTS; attempt++) {
            boolean canScrollMore = scrollDown(container);

            if (!getDriver().findElements(targetLocator).isEmpty()) {
                return visible(targetLocator);
            }

            if (!canScrollMore) {
                break;
            }
        }

        throw new NoSuchElementException(
                "Element " + targetLocator
                        + " was not found in container " + containerLocator
                        + " after at most " + MAX_SCROLL_ATTEMPTS + " scroll attempts"
        );
    }
}
