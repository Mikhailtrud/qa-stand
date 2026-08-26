package pages;

import config.TestConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final AndroidDriver driver;
    private final WebDriverWait wait;

    protected BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.explicitWaitSeconds()));
    }

    protected By tag(String testTag) {
        return AppiumBy.accessibilityId(testTag);
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void type(String testTag, String value) {
        visible(tag(testTag)).click();
        WebElement input = driver.switchTo().activeElement();
        input.clear();
        input.sendKeys(value);
    }

    protected void tapAfterHidingKeyboard(String testTag) {
        try {
            driver.hideKeyboard();
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
}
