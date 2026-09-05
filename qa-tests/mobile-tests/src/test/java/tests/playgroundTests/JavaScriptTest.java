package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

@Epic("QA Stand Mobile")
@Feature("QA Playground")
@Story("Dialogs")
public class JavaScriptTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        usersSteps.openPlayground();
        playgroundDialogsSteps.verifyPlaygroundDisplayed();
    }

    @Test
    @Description("The alert can be opened and closed")
    void alertCanBeClosed() {
        playgroundDialogsSteps
                .openAlert()
                .verifyAlertWindowVisible()
                .closeAlert()
                .verifyAlertWindowClosed();
    }

    @Test
    @Description("The confirm dialog closes after accepting it")
    void confirmCanBeAccepted() {
        playgroundDialogsSteps
                .openConfirm()
                .verifyConfirmWindowVisible()
                .acceptConfirm()
                .verifyConfirmWindowClosed();
    }

    @Test
    @Description("The confirm dialog closes after cancelling it")
    void confirmCanBeCancelled() {
        playgroundDialogsSteps
                .openConfirm()
                .verifyConfirmWindowVisible()
                .cancelConfirm()
                .verifyConfirmWindowClosed();
    }

    @Test
    @Description("The prompt closes after submitting entered text")
    void promptCanBeSubmitted() {
        playgroundDialogsSteps
                .openPrompt()
                .verifyPromptWindowVisible()
                .fillPromptInput("Test")
                .submitPrompt()
                .verifyPromptWindowClosed();
    }

    @Test
    @Description("The prompt closes after cancelling entered text")
    void promptCanBeCancelled() {
        playgroundDialogsSteps
                .openPrompt()
                .verifyPromptWindowVisible()
                .fillPromptInput("Test")
                .cancelPrompt()
                .verifyPromptWindowClosed();
    }

    @Test
    @Description("The toast displays the successful operation message")
    void toastDisplaysSuccessMessage() {
        playgroundDialogsSteps
                .openToast()
                .verifyToast("Operation completed successfully");
    }

    @Test
    @Description("The modal window can be opened and closed")
    void modalCanBeClosed() {
        playgroundDialogsSteps
                .openModal()
                .verifyModalWindowVisible()
                .closeModal()
                .verifyModalWindowClosed();
    }
}
