package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public final class PlaygroundPage extends BasePage {
    private final By screen = tag("playground_screen");
    private final By usersButton = tag("open_users_button");
    private final By textInput = tag("playground_text_input");
    private final By checkbox = tag("playground_checkbox");
    private final By alertButton = tag("playground_alert_button");
    private final By table = tag("dynamic_table");

    public PlaygroundPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isDisplayed(screen);
    }

    public PlaygroundPage enterText(String value) {
        type("playground_text_input", value);
        return this;
    }

    public void toggleAcceptTerms() {
        clickable(checkbox).click();
    }

    public void openAlert() {
        clickable(alertButton).click();
    }

    public boolean isTableDisplayed() {
        return isDisplayed(table);
    }

    public void openUsers() {
        clickable(usersButton).click();
    }
}
