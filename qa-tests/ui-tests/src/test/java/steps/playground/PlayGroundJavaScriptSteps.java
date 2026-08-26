package steps.playground;

import com.codeborne.selenide.Condition;
import steps.BaseSteps;

public class PlayGroundJavaScriptSteps extends BaseSteps {

    public PlayGroundJavaScriptSteps openPlayGroundPage() {
        step("Открыть страницу PlayGround");
        playGroundPage.open();
        return this;
    }

    public PlayGroundJavaScriptSteps verifyPlaygroundTabsVisible() {
        step("Проверить видимость блока с JavaScript");
        playGroundPage.javaScript().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundJavaScriptSteps clickOnAlertButtonStep(String expectedText) {
        step("Проверить появление алерат, его текст и закрыть алерт");
        playGroundJavaScriptPage.alertButton().click();
        playGroundJavaScriptPage.verifyAlertText(expectedText);
        playGroundJavaScriptPage.verifyAlertClosed();
        return this;
    }

    public PlayGroundJavaScriptSteps clickOnAcceptConfirmButtonStep(String expectedText) {
        step("Проверить появление Confirm, его текст и нажать на кнопку Accept");
        playGroundJavaScriptPage.confirmButton().click();
        playGroundJavaScriptPage.confirmAccept(expectedText);
        playGroundJavaScriptPage.verifyAlertClosed();
        return this;
    }

    public PlayGroundJavaScriptSteps clickOnCancelConfirmButtonStep(String expectedText) {
        step("Проверить появление Confirm, его текст и нажать на кнопку Cancel");
        playGroundJavaScriptPage.confirmButton().click();
        playGroundJavaScriptPage.confirmDismiss(expectedText);
        playGroundJavaScriptPage.verifyAlertClosed();
        return this;
    }


    public PlayGroundJavaScriptSteps clickOnAcceptPromptButtonStep(String expectedText, String inputText) {
        step("Проверить появление Prompt, его текст и нажать на кнопку Accept");
        playGroundJavaScriptPage.promptButton().click();
        playGroundJavaScriptPage.promptAccept(expectedText, inputText);
        playGroundJavaScriptPage.verifyAlertClosed();
        return this;
    }

    public PlayGroundJavaScriptSteps clickOnCancelPromptButtonStep(String expectedText, String inputText) {
        step("Проверить появление Prompt, его текст и нажать на кнопку Cancel");
        playGroundJavaScriptPage.promptButton().click();
        playGroundJavaScriptPage.promptAccept(expectedText, inputText);
        playGroundJavaScriptPage.verifyAlertClosed();
        return this;
    }

    public PlayGroundJavaScriptSteps clickOnToastButtonStep() {
        step("Нажать на кнопку вызова Toast");
        playGroundJavaScriptPage.toastButton().click();
        return this;
    }

    public PlayGroundJavaScriptSteps verifyToastTextValue(String expectedText) {
        step("Проверить текст в toast окне: " + expectedText);
        playGroundJavaScriptPage.verifyToastValue(expectedText);
        return this;
    }

    public PlayGroundJavaScriptSteps verifyPlaygroundToastBlockVisible() {
        step("Выбрать опцию");
        playGroundJavaScriptPage.toastWindow().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundJavaScriptSteps clickOnModalButtonStep() {
        step("Нажать на кнопку вызова Modal");
        playGroundJavaScriptPage.modalButton().click();
        return this;
    }

    public PlayGroundJavaScriptSteps verifyModalWindowTitle(String expectedText) {
        step("Проверить заголовок модальном окне: " + expectedText);
        playGroundJavaScriptPage.verifyModalWindowTitle(expectedText);
        return this;
    }

    public PlayGroundJavaScriptSteps verifyModalWindowDescription(String expectedText) {
        step("Проверить описание модальном окне: " + expectedText);
        playGroundJavaScriptPage.verifyModalWindowDescription(expectedText);
        return this;
    }

    public PlayGroundJavaScriptSteps verifyModalWindowVisible() {
        step("Проверить отображение таблицы пользователей");
        playGroundJavaScriptPage.modalWindow().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundJavaScriptSteps closeModalWindowStep() {
        step("Закрыть модальное окно");
        playGroundJavaScriptPage.modalWindowCloseButton().click();
        return this;
    }

    public PlayGroundJavaScriptSteps verifyModalWindowNotVisible() {
        step("Проверить модальное окно не видно ");
        playGroundJavaScriptPage.modalWindow().shouldNotBe(Condition.visible);
        return this;
    }


}
