package pages;

import org.openqa.selenium.By;

public final class PlaygroundDialogsPage extends BasePage {
    private final By playgroundScreen = tag("playground_screen");
    private final By alertButton = tag("playground_alert_button");
    private final By alertDialog = tag("alert_dialog");
    private final By okAlertButton = tag("alert_dialog_close_button");
    private final By confirmButton = tag("playground_confirm_button");
    private final By confirmDialog = tag("confirm_dialog");
    private final By okConfirmButton = tag("confirm_dialog_close_button");
    private final By cancelConfirmButton = tag("confirm_dialog_cancel_button");
    private final By promptButton = tag("playground_prompt_button");
    private final By promptDialog = tag("prompt_dialog");
    private final By promptInput = tag("prompt_input");
    private final By promptOkButton = tag("prompt_ok_button");
    private final By promptCancelButton = tag("prompt_cancel_button");
    private final By toastButton = tag("playground_toast_button");
    private final By toastMessage = tag("toast_message");
    private final By modalButton = tag("open_modal_button");
    private final By modalWindow = tag("modal_window");
    private final By modalWindowCloseButton = tag("modal_window_close_button");

    public void openAlert() {
        scrollToElement(playgroundScreen, alertButton).click();
    }

    public boolean isAlertWindowDisplayed() {
        return isDisplayed(alertDialog);
    }

    public void closeAlert() {
        clickable(okAlertButton).click();
    }

    public boolean isAlertWindowClosed() {
        return !isDisplayed(alertDialog);
    }

    public void openConfirm() {
        scrollToElement(playgroundScreen, confirmButton).click();
    }

    public boolean isConfirmWindowDisplayed() {
        return isDisplayed(confirmDialog);
    }

    public void acceptConfirm() {
        clickable(okConfirmButton).click();
    }

    public void cancelConfirm() {
        clickable(cancelConfirmButton).click();
    }

    public boolean isConfirmWindowClosed() {
        return !isDisplayed(confirmDialog);
    }

    public void openPrompt() {
        scrollToElement(playgroundScreen, promptButton).click();
    }

    public boolean isPromptWindowDisplayed() {
        return isDisplayed(promptDialog);
    }

    public void enterTextPromptInput(String value) {
        type(promptInput, value);
    }

    public void submitPrompt() {
        clickable(promptOkButton).click();
    }

    public void cancelPrompt() {
        clickable(promptCancelButton).click();
    }

    public boolean isPromptWindowClosed() {
        return !isDisplayed(promptDialog);
    }

    public void openToast() {
        scrollToElement(playgroundScreen, toastButton).click();
    }

    public String getToastText() {
        return visible(toastMessage).getText();
    }

    public void openModal() {
        scrollToElement(playgroundScreen, modalButton).click();
    }

    public boolean isModalWindowDisplayed() {
        return isDisplayed(modalWindow);
    }

    public void closeModal() {
        clickable(modalWindowCloseButton).click();
    }

    public boolean isModalWindowClosed() {
        return !isDisplayed(modalWindow);
    }

    public boolean isDisplayed() {
        return isDisplayed(playgroundScreen);
    }
}
