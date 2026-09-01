package framework.utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class AllureAttachments {
    private AllureAttachments() {
    }

    public static void attachFailureState(AndroidDriver driver) {
        if (driver == null) {
            return;
        }
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (RuntimeException ignored) {
            // A dead Appium session should not hide the original test failure.
        }
        try {
            byte[] source = driver.getPageSource().getBytes(StandardCharsets.UTF_8);
            Allure.addAttachment("Page source", "application/xml", new ByteArrayInputStream(source), ".xml");
        } catch (RuntimeException ignored) {
            // A dead Appium session should not hide the original test failure.
        }
    }
}
