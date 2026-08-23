package tests.playGroundTests;

import config.AuthenticatedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaScriptTests extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundJavaScriptSteps.openPlayGroundPage().verifyPlaygroundTabsVisible();
    }

    @Test
    void checkAlertTest(){
        playGroundJavaScriptSteps.clickOnAlertButtonStep("Test Alert");
    }

    @Test
    void checkAcceptConfirmTest(){
        playGroundJavaScriptSteps.clickOnAcceptConfirmButtonStep("Are you sure?");
    }

    @Test
    void checkCancelConfirmTest(){
        playGroundJavaScriptSteps.clickOnCancelConfirmButtonStep("Are you sure?");
    }

    @Test
    void checkAcceptPromptTest(){
        playGroundJavaScriptSteps.clickOnAcceptPromptButtonStep("Enter your name", "Test");
    }

    @Test
    void checkCancelPromptTest(){
        playGroundJavaScriptSteps.clickOnCancelPromptButtonStep("Enter your name", "Test");
    }

    @Test
    void checkToastTest(){
        playGroundJavaScriptSteps.clickOnToastButtonStep().verifyPlaygroundToastBlockVisible().verifyToastTextValue("Operation completed successfully");
    }

    @Test
    void checkModalTest(){
        playGroundJavaScriptSteps.clickOnModalButtonStep().verifyModalWindowTitle("Modal Window").verifyModalWindowDescription("This modal is used for UI automation testing.");
    }

    @Test
    void checkCloseModalTest() {
        playGroundJavaScriptSteps.clickOnModalButtonStep().verifyModalWindowVisible().closeModalWindowStep().verifyModalWindowNotVisible();
    }
}

