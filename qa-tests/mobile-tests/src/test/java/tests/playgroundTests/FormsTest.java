package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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

@Epic("QA Stand Mobile")
@Feature("QA Playground")
@Story("Forms")
public class FormsTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        usersSteps.openPlayground();
        playgroundFormsSteps.verifyPlaygroundDisplayed();
    }

    @Test
    @Description("Entered text is displayed in the text input")
    void textInputDisplaysEnteredValue() {
        String text = "Test input text";

        playgroundFormsSteps
                .fillInput(text)
                .verifyInputValue(text);
    }

    @Test
    @Description("Entered text is displayed in the textarea")
    void textareaDisplaysEnteredValue() {
        String text = "Test textarea text";

        playgroundFormsSteps
                .fillTextarea(text)
                .verifyTextareaValue(text);
    }

    @Test
    @Description("A date from the previous month can be selected")
    void previousMonthDateCanBeSelected() {
        LocalDate targetDate = LocalDate.now()
                .minusYears(1)
                .minusMonths(1)
                .withDayOfMonth(11);

        playgroundFormsSteps
                .selectDate(targetDate.getYear(), targetDate.getDayOfMonth())
                .verifySelectedDate(targetDate.toString());
    }

    @Test
    @Description("The selected option is displayed in the select field")
    void optionCanBeSelected() {
        playgroundFormsSteps
                .openSelect()
                .verifyOptionDisplayed(1)
                .selectOption(2)
                .verifyOptionSelected("Option 2");
    }

    @Test
    @Description("Multiple options can be selected and displayed")
    void multipleOptionsCanBeSelected() {
        playgroundFormsSteps
                .openMultiSelect()
                .selectMultiOptions("java", "kotlin", "groovy")
                .verifyOptionsSelected("java", "kotlin", "groovy")
                .closeMultiSelect()
                .verifyMultiSelectSummary("Java, Kotlin, Groovy");
    }

    @Test
    @Description("An uploaded file name is displayed in the form")
    void fileCanBeUploaded() throws IOException, URISyntaxException {
        String fileName = "test.png";
        File uploadFile = Path.of(Objects.requireNonNull(
                FormsTest.class.getResource("/files/" + fileName),
                "Upload test resource not found: " + fileName
        ).toURI()).toFile();

        DriverManager.getDriver().pushFile(
                "/sdcard/Download/" + fileName,
                uploadFile
        );

        playgroundFormsSteps
                .openFileChooser()
                .chooseFile(fileName)
                .verifyFileSelected();
    }

    @Test
    @Description("The Accept Terms checkbox can be selected and cleared")
    void acceptTermsCanBeToggled() {

        playgroundFormsSteps
                .verifyAcceptTermsState(false)
                .toggleAcceptTerms()
                .verifyAcceptTermsState(true)
                .toggleAcceptTerms()
                .verifyAcceptTermsState(false);
    }

    @Test
    @Description("Selecting Male clears the Female radio option")
    void maleRadioOptionCanBeSelected() {

        playgroundFormsSteps
                .selectRadio("male")
                .verifyRadioState("male", true)
                .verifyRadioState("female", false);
    }

    @Test
    @Description("Selecting Female clears the Male radio option")
    void femaleRadioOptionCanBeSelected() {

        playgroundFormsSteps
                .selectRadio("female")
                .verifyRadioState("female", true)
                .verifyRadioState("male", false);
    }
}
