package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class JavaScriptTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        playgroundSteps
                .openPlayground()
                .verifyPlaygroundDisplayed();
    }

    @Test
    @Description("")
    void alertTest() {
        playgroundSteps
                .openAlert()
                .verifyAlertWindowVisible()
                .closeAlert();
    }

    @Test
    @Description("")
    void confirmTest() {
        playgroundSteps
                .openConfirm()
                .verifyConfirmWindowVisible()
                .clickOnOkConfirmButton();
    }

    @Test
    @Description("")
    void cancelConfirmTest() {
        playgroundSteps
                .openConfirm()
                .verifyConfirmWindowVisible()
                .cancelConfirm();
    }

    @Test
    @Description("")
    void promptTest() {
        playgroundSteps
                .openPrompt()
                .verifyPromptWindowVisible()
                .fillPromptInput("Test")
                .clickOnOkPromptButton();
    }

    @Test
    @Description("")
    void cancelPromptTest() {
        playgroundSteps
                .openPrompt()
                .verifyPromptWindowVisible()
                .fillPromptInput("Test")
                .cancelPrompt();
    }

    @Test
    @Description("")
    void toastText() {
        playgroundSteps
                .openToast()
                .verifyToast("Operation completed successfully");
    }

    @Test
    @Description("")
    void modalTest() {
        playgroundSteps
                .openModal()
                .verifyAModalWindowVisible()
                .closeModal();
    }
}
