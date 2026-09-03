package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import framework.driver.DriverManager;
import java.io.File;

import java.io.IOException;
import java.time.LocalDate;

public class FormsTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    private static final String TEST_FILE_NAME = "test-upload.xml";
    private static final String TEST_FILE_PATH = "src/test/resources/files/" + TEST_FILE_NAME;

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        playgroundSteps
                .openPlayground()
                .verifyPlaygroundDisplayed();
    }

    @Test
    @Description("")
    void inputTest() {
        playgroundSteps.fillInput("Test input text");
    }

    @Test
    @Description("")
    void textareaTest() {
        playgroundSteps.fillTextarea("Test textarea text");
    }

    @Test
    @Description("")
    void selectDateTest() {
        LocalDate targetDate = LocalDate.now()
                .minusYears(1)
                .minusMonths(1)
                .withDayOfMonth(11);

        playgroundSteps
                .selectDate(targetDate.getYear(), targetDate.getDayOfMonth())
                .verifySelectedDate(targetDate.toString());
    }

    @Test
    @Description("")
    void getOptionTest() {
        playgroundSteps
                .selectOption()
                .verifySaveButtonEnabled(1)
                .selectOption(2)
                .verifyOptionSelected("Option 2");
    }

    @Test
    @Description("")
    void multiSelectTest() {
        playgroundSteps
                .openMultiSelect()
                .selectOptions("java", "kotlin", "groovy")
                .verifyOptionsSelected("java", "kotlin", "groovy")
                .closeMultiSelect()
                .verifyMultiOptionSelected("Java, Kotlin, Groovy");
    }

    @Test
    @Description("")
    void uploadFileTest() throws IOException {
        String fileName = "test.png";

        DriverManager.getDriver().pushFile(
                "/sdcard/Download/" + fileName,
                new File("src/test/resources/files/" + fileName)
        );

        playgroundSteps
                .openChooseFile()
                .chooseFile(fileName)
                .verifyFileSelected();
    }

    @Test
    @Description("")
    void acceptTermsTest() {

        playgroundSteps
                .verifyAcceptTermsState(false)
                .selectAcceptTerms()
                .verifyAcceptTermsState(true)
                .selectAcceptTerms()
                .verifyAcceptTermsState(false);
    }

    @Test
    @Description("")
    void radioButtonsMaleTest() {

        playgroundSteps
                .selectRadio("male")
                .verifyRadioState("male", true)
                .verifyRadioState("female", false);
    }

    @Test
    @Description("")
    void radioButtonsFemaleTest() {

        playgroundSteps
                .selectRadio("female")
                .verifyRadioState("female", true)
                .verifyRadioState("male", false);
    }
}
