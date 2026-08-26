package pages.playground;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoAlertPresentException;
import pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayGroundJavaScriptPage extends BasePage {

    //Elements
    //JavaScript
    private final SelenideElement alertButton =
            $("[data-testid='playground-alert-button']");

    private final SelenideElement confirmButton =
            $("[data-testid='playground-confirm-button']");

    private final SelenideElement promptButton =
            $("[data-testid='playground-prompt-button']");

    private final SelenideElement toastButton =
            $("[data-testid='playground-toast-button']");

    private final SelenideElement toastWindow =
            $("[data-testid='toast']");

    // JavaScript Modal window
    private final SelenideElement modalButton =
            $("[data-testid='open-modal-button']");

    private final SelenideElement modalWindowText =
            $("[data-testid='modal-window'] h3");

    private final SelenideElement modalWindow =
            $("[data-testid='modal-window']");

    private final SelenideElement modalWindowDescription =
            $("[data-testid='modal-window'] p");

    private final SelenideElement modalWindowCloseButton =
            $("[data-testid='close-modal-button']");

    //Functions
    public SelenideElement alertButton() {
        return alertButton;
    }

    public PlayGroundJavaScriptPage verifyAlertText(String expectedText) {
        String actualText = switchTo().alert().getText();

        assertEquals(expectedText, actualText);

        switchTo().alert().accept();

        return this;
    }

    public PlayGroundJavaScriptPage verifyAlertClosed() {
        assertThrows(
                NoAlertPresentException.class,
                () -> getWebDriver().switchTo().alert()
        );

        return this;
    }

    public SelenideElement confirmButton() {
        return confirmButton;
    }

    public PlayGroundJavaScriptPage confirmAccept(String expectedText) {
        String actualText = switchTo().alert().getText();

        assertEquals(expectedText, actualText);

        switchTo().alert().accept();

        return this;
    }

    public PlayGroundJavaScriptPage confirmDismiss(String expectedText) {
        String actualText = switchTo().alert().getText();

        assertEquals(expectedText, actualText);

        switchTo().alert().dismiss();

        return this;
    }

    public SelenideElement promptButton() {
        return promptButton;
    }

    public PlayGroundJavaScriptPage promptAccept(
            String expectedText,
            String inputText
    ) {
        String actualText = switchTo().alert().getText();

        assertEquals(expectedText, actualText);

        switchTo().alert().sendKeys(inputText);
        switchTo().alert().accept();

        return this;
    }

    public PlayGroundJavaScriptPage promptDismiss(String expectedText) {
        String actualText = switchTo().alert().getText();

        assertEquals(expectedText, actualText);

        switchTo().alert().dismiss();

        return this;
    }

    public SelenideElement toastButton() {
        return toastButton;
    }

    public SelenideElement toastWindow() {
        return toastWindow;
    }

    public PlayGroundJavaScriptPage verifyToastValue(String expectedText) {
        $("[data-testid='toast']")
                .shouldHave(text(expectedText));

        return this;
    }

    public SelenideElement modalButton() {
        return modalButton;
    }

    public PlayGroundJavaScriptPage verifyModalWindowTitle(String expectedText) {
        $(modalWindowText)
                .shouldHave(text(expectedText));

        return this;
    }

    public PlayGroundJavaScriptPage verifyModalWindowDescription(String expectedText) {
        $(modalWindowDescription)
                .shouldHave(text(expectedText));

        return this;
    }

    public SelenideElement modalWindowCloseButton() {
        return modalWindowCloseButton;
    }

    public SelenideElement modalWindow() {
        return modalWindow;
    }



}
