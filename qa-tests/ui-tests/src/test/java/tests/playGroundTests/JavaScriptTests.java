package tests.playGroundTests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Epic("QA Stand")
@Feature("Playground: JavaScript")
public class JavaScriptTests extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundJavaScriptSteps
                .openPlayGroundPage()
                .verifyPlaygroundJavaScriptVisible();
    }

    @Test
    @Story("Alert")
    void checkAlertTest(){
        playGroundJavaScriptSteps
                .clickOnAlertButtonStep("Test Alert");
    }

    @Test
    @Story("Confirm")
    void checkAcceptConfirmTest(){
        playGroundJavaScriptSteps
                .clickOnAcceptConfirmButtonStep("Are you sure?")
                .verifyDialogResultVisible()
                .verifyDialogResultText("Confirm accepted");
    }

    @Test
    @Story("Confirm")
    void checkCancelConfirmTest(){
        playGroundJavaScriptSteps
                .clickOnCancelConfirmButtonStep("Are you sure?")
                .verifyDialogResultVisible()
                .verifyDialogResultText("Confirm dismissed");
    }

    @Test
    @Story("Prompt")
    void checkAcceptPromptTest(){
        playGroundJavaScriptSteps
                .clickOnAcceptPromptButtonStep("Enter your name", "Test")
                .verifyDialogResultVisible()
                .verifyDialogResultText("Prompt accepted: Test");
    }

    @Test
    @Story("Prompt")
    void checkCancelPromptTest(){
        playGroundJavaScriptSteps
                .clickOnCancelPromptButtonStep("Enter your name")
                .verifyDialogResultVisible()
                .verifyDialogResultText("Prompt dismissed");
    }

    @Test
    @Story("Toast")
    void checkToastTest(){
        playGroundJavaScriptSteps
                .clickOnToastButtonStep()
                .verifyPlaygroundToastBlockVisible()
                .verifyToastTextValue("Operation completed successfully");
    }

    @Test
    @Story("Modal")
    void checkModalTest(){
        playGroundJavaScriptSteps
                .clickOnModalButtonStep()
                .verifyModalWindowVisible()
                .verifyModalWindowTitle("Modal Window")
                .verifyModalWindowDescription("This modal is used for UI automation testing.");
    }

    @Test
    @Story("Modal")
    void checkCloseModalTest() {
        playGroundJavaScriptSteps
                .clickOnModalButtonStep()
                .verifyModalWindowVisible()
                .closeModalWindowStep()
                .verifyModalWindowNotVisible();
    }
}

