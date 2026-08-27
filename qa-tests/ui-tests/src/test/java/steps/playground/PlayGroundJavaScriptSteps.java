package steps.playground;

import com.codeborne.selenide.Condition;
import steps.BaseSteps;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayGroundJavaScriptSteps extends BaseSteps {

    @Step("Open the Playground page")
    public PlayGroundJavaScriptSteps openPlayGroundPage() {
        playGroundPage.open();
        return this;
    }

    @Step("Verify that the JavaScript section is visible")
    public PlayGroundJavaScriptSteps verifyPlaygroundJavaScriptVisible() {
        playGroundPage.javaScript().shouldBe(Condition.visible);
        return this;
    }

    @Step("Open the alert, verify text {expectedText}, and accept it")
    public PlayGroundJavaScriptSteps clickOnAlertButtonStep(String expectedText) {
        playGroundJavaScriptPage.alertButton().click();
        verifyAlertText(expectedText);
        playGroundJavaScriptPage.acceptAlert();
        verifyAlertClosed();
        return this;
    }

    @Step("Open the confirm, verify text {expectedText}, and accept it")
    public PlayGroundJavaScriptSteps clickOnAcceptConfirmButtonStep(String expectedText) {
        playGroundJavaScriptPage.confirmButton().click();
        verifyAlertText(expectedText);
        playGroundJavaScriptPage.confirmAccept();
        verifyAlertClosed();
        return this;
    }

    @Step("Open the confirm, verify text {expectedText}, and dismiss it")
    public PlayGroundJavaScriptSteps clickOnCancelConfirmButtonStep(String expectedText) {
        playGroundJavaScriptPage.confirmButton().click();
        verifyAlertText(expectedText);
        playGroundJavaScriptPage.confirmDismiss();
        verifyAlertClosed();
        return this;
    }


    @Step("Open the prompt, verify text {expectedText}, enter a value, and accept it")
    public PlayGroundJavaScriptSteps clickOnAcceptPromptButtonStep(String expectedText, String inputText) {
        playGroundJavaScriptPage.promptButton().click();
        verifyAlertText(expectedText);
        playGroundJavaScriptPage.promptAccept(inputText);
        verifyAlertClosed();
        return this;
    }

    @Step("Open the prompt, verify text {expectedText}, and dismiss it")
    public PlayGroundJavaScriptSteps clickOnCancelPromptButtonStep(String expectedText) {
        playGroundJavaScriptPage.promptButton().click();
        verifyAlertText(expectedText);
        playGroundJavaScriptPage.promptDismiss();
        verifyAlertClosed();
        return this;
    }

    @Step("Show the toast")
    public PlayGroundJavaScriptSteps clickOnToastButtonStep() {
        playGroundJavaScriptPage.toastButton().click();
        return this;
    }

    @Step("Verify toast text: {expectedText}")
    public PlayGroundJavaScriptSteps verifyToastTextValue(String expectedText) {
        playGroundJavaScriptPage.verifyToastValue(expectedText);
        return this;
    }

    @Step("Verify that the toast is visible")
    public PlayGroundJavaScriptSteps verifyPlaygroundToastBlockVisible() {
        playGroundJavaScriptPage.toastWindow().shouldBe(Condition.visible);
        return this;
    }

    @Step("Open the modal")
    public PlayGroundJavaScriptSteps clickOnModalButtonStep() {
        playGroundJavaScriptPage.modalButton().click();
        return this;
    }

    @Step("Verify modal title: {expectedText}")
    public PlayGroundJavaScriptSteps verifyModalWindowTitle(String expectedText) {
        playGroundJavaScriptPage.verifyModalWindowTitle(expectedText);
        return this;
    }

    @Step("Verify modal description: {expectedText}")
    public PlayGroundJavaScriptSteps verifyModalWindowDescription(String expectedText) {
        playGroundJavaScriptPage.verifyModalWindowDescription(expectedText);
        return this;
    }

    @Step("Verify that the modal is visible")
    public PlayGroundJavaScriptSteps verifyModalWindowVisible() {
        playGroundJavaScriptPage.modalWindow().shouldBe(Condition.visible);
        return this;
    }

    @Step("Close the modal")
    public PlayGroundJavaScriptSteps closeModalWindowStep() {
        playGroundJavaScriptPage.modalWindowCloseButton().click();
        return this;
    }

    @Step("Verify that the modal is not visible")
    public PlayGroundJavaScriptSteps verifyModalWindowNotVisible() {
        playGroundJavaScriptPage.modalWindow().shouldNotBe(Condition.visible);
        return this;
    }

    @Step("Verify that the JavaScript dialog result is visible")
    public PlayGroundJavaScriptSteps verifyDialogResultVisible() {
        playGroundJavaScriptPage.dialogResult().shouldBe(Condition.visible);
        return this;
    }

    @Step("Verify JavaScript dialog result: {expectedText}")
    public PlayGroundJavaScriptSteps verifyDialogResultText(String expectedText) {
        playGroundJavaScriptPage.verifyDialogResultText(expectedText);
        return this;
    }

    private void verifyAlertText(String expectedText) {
        assertEquals(expectedText, playGroundJavaScriptPage.alertText());
    }

    private void verifyAlertClosed() {
        assertFalse(playGroundJavaScriptPage.isAlertPresent(), "Browser dialog must be closed");
    }


}
