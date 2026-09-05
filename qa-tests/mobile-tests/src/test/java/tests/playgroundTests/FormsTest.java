package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import framework.driver.DriverManager;
import java.io.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects;

public class FormsTest extends BaseTest {
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
    void inputTest() {
        String text = "Test input text";

        playgroundSteps
                .fillInput(text)
                .verifyInputValue(text);
    }

    @Test
    @Description("")
    void textareaTest() {
        String text = "Test textarea text";

        playgroundSteps
                .fillTextarea(text)
                .verifyTextareaValue(text);
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
    void uploadFileTest() throws IOException, URISyntaxException {
        String fileName = "test.png";
        File uploadFile = Path.of(Objects.requireNonNull(
                FormsTest.class.getResource("/files/" + fileName),
                "Upload test resource not found: " + fileName
        ).toURI()).toFile();

        DriverManager.getDriver().pushFile(
                "/sdcard/Download/" + fileName,
                uploadFile
        );

        playgroundSteps
                .openChooseFile()
                .chooseFile(fileName)
                .verifyFileSelected(fileName);
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
