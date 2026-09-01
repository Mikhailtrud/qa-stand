package pages;

import framework.config.AppiumConfig;
import framework.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;

public abstract class BasePage {
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

    protected WebElement clickable(By locator) {
        return explicitWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void type(String testTag, String value) {
        visible(tag(testTag)).click();
        WebElement input = getDriver().switchTo().activeElement();
        input.clear();
        input.sendKeys(value);
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
        } catch (RuntimeException ignored) {
            return false;
        }
    }
    protected void swipeUp() {
        getDriver().executeScript(
                "mobile: swipeGesture",
                java.util.Map.of(
                        "left", 100,
                        "top", 500,
                        "width", 800,
                        "height", 1200,
                        "direction", "up",
                        "percent", 0.75
                )
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

        while (DriverManager.getDriver().findElements(targetLocator).isEmpty()) {
            if (!scrollDown(container)) {
                throw new NoSuchElementException(
                        "Element not found after scrolling: " + targetLocator
                );
            }
        }

        return visible(targetLocator);
    }
}
