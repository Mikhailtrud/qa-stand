package tests;

import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;
import utils.AllureAttachments;

public abstract class BaseTest {
    protected AndroidDriver driver;

    @RegisterExtension
    final AfterTestExecutionCallback failureAttachment = context ->
            context.getExecutionException().ifPresent(error -> AllureAttachments.attachFailureState(driver));

    @BeforeEach
    void startDriver() {
        driver = DriverManager.start();
    }

    @AfterEach
    void stopDriver() {
        DriverManager.quit();
        driver = null;
    }
}
