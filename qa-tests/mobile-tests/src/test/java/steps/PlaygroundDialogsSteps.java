package steps;

import io.qameta.allure.Step;
import pages.PlaygroundDialogsPage;

import static org.assertj.core.api.Assertions.assertThat;

public final class PlaygroundDialogsSteps extends BaseSteps {
    private final PlaygroundDialogsPage playgroundDialogsPage = new PlaygroundDialogsPage();

    @Step("Verify QA Playground is displayed")
    public PlaygroundDialogsSteps verifyPlaygroundDisplayed() {
        assertThat(playgroundDialogsPage.isDisplayed()).isTrue();
        return this;
    }

    @Step("Open alert")
    public PlaygroundDialogsSteps openAlert() {
        playgroundDialogsPage.openAlert();
        return this;
    }

    @Step("Verify alert window visible")
    public PlaygroundDialogsSteps verifyAlertWindowVisible() {
        assertThat(playgroundDialogsPage.isAlertWindowDisplayed()).isTrue();
        return this;
    }

    @Step("Close alert")
    public PlaygroundDialogsSteps closeAlert() {
        playgroundDialogsPage.closeAlert();
        return this;
    }

    @Step("Verify alert window closed")
    public PlaygroundDialogsSteps verifyAlertWindowClosed() {
        assertThat(playgroundDialogsPage.isAlertWindowClosed()).isTrue();
        return this;
    }

    @Step("Open confirm")
    public PlaygroundDialogsSteps openConfirm() {
        playgroundDialogsPage.openConfirm();
        return this;
    }

    @Step("Verify confirm window visible")
    public PlaygroundDialogsSteps verifyConfirmWindowVisible() {
        assertThat(playgroundDialogsPage.isConfirmWindowDisplayed()).isTrue();
        return this;
    }

    @Step("Accept the confirm dialog")
    public PlaygroundDialogsSteps acceptConfirm() {
        playgroundDialogsPage.acceptConfirm();
        return this;
    }

    @Step("Cancel the confirm dialog")
    public PlaygroundDialogsSteps cancelConfirm() {
        playgroundDialogsPage.cancelConfirm();
        return this;
    }

    @Step("Verify confirm window closed")
    public PlaygroundDialogsSteps verifyConfirmWindowClosed() {
        assertThat(playgroundDialogsPage.isConfirmWindowClosed()).isTrue();
        return this;
    }

    @Step("Open prompt")
    public PlaygroundDialogsSteps openPrompt() {
        playgroundDialogsPage.openPrompt();
        return this;
    }

    @Step("Fill forms prompt input")
    public PlaygroundDialogsSteps fillPromptInput(String text) {
        playgroundDialogsPage.enterTextPromptInput(text);
        return this;
    }

    @Step("Verify prompt window visible")
    public PlaygroundDialogsSteps verifyPromptWindowVisible() {
        assertThat(playgroundDialogsPage.isPromptWindowDisplayed()).isTrue();
        return this;
    }

    @Step("Submit the prompt dialog")
    public PlaygroundDialogsSteps submitPrompt() {
        playgroundDialogsPage.submitPrompt();
        return this;
    }

    @Step("Cancel the prompt dialog")
    public PlaygroundDialogsSteps cancelPrompt() {
        playgroundDialogsPage.cancelPrompt();
        return this;
    }

    @Step("Verify prompt window closed")
    public PlaygroundDialogsSteps verifyPromptWindowClosed() {
        assertThat(playgroundDialogsPage.isPromptWindowClosed()).isTrue();
        return this;
    }

    @Step("Open toast")
    public PlaygroundDialogsSteps openToast() {
        playgroundDialogsPage.openToast();
        return this;
    }

    @Step("Verify toast text: {expectedText}")
    public PlaygroundDialogsSteps verifyToast(String expectedText) {
        assertThat(playgroundDialogsPage.getToastText()).isEqualTo(expectedText);
        return this;
    }

    @Step("Open modal window")
    public PlaygroundDialogsSteps openModal() {
        playgroundDialogsPage.openModal();
        return this;
    }

    @Step("Verify modal window visible")
    public PlaygroundDialogsSteps verifyModalWindowVisible() {
        assertThat(playgroundDialogsPage.isModalWindowDisplayed()).isTrue();
        return this;
    }

    @Step("Close modal window")
    public PlaygroundDialogsSteps closeModal() {
        playgroundDialogsPage.closeModal();
        return this;
    }

    @Step("Verify modal window closed")
    public PlaygroundDialogsSteps verifyModalWindowClosed() {
        assertThat(playgroundDialogsPage.isModalWindowClosed()).isTrue();
        return this;
    }
}
