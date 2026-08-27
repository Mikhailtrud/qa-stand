package tests.playGroundTests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

@Epic("QA Stand")
@Feature("Playground: Forms")
public class FormsTests extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundFormsSteps
                .openPlayGroundPage()
                .verifyPlaygroundFormsVisible();
    }

    @Test
    @Story("Text input")
    void fillTextInputTest() {
        playGroundFormsSteps
                .enterText("My text")
                .verifyTextInputValue("My text");
    }

    @Test
    @Story("Textarea")
    void fillTextareaTest() {
        playGroundFormsSteps
                .enterTextarea("My textarea")
                .verifyTextareaValue("My textarea");
    }

    @Test
    @Story("Date input")
    void fillDateTest() {
        playGroundFormsSteps
                .enterDate("20.02.1988")
                .verifyDateValue("1988-02-20");
    }

    @Test
    @Story("Single select")
    void selectOptionTest() {
        playGroundFormsSteps
                .verifyOptionVisible(2)
                .selectOption(2)
                .verifyOptionValue("Option 2");
    }

    @Test
    @Story("Multiple select")
    void selectMultipleOptionsTest() {
        playGroundFormsSteps
                .selectMultiOption(1, 2, 3)
                .verifySelectedOptions("Kotlin", "Scala", "Groovy");
    }

    @Test
    @Story("File upload")
    void uploadFileTest() {
        Path file = Path.of("src/test/resources/files/test.png");

        playGroundFormsSteps
                .uploadFile(file)
                .verifyFileUploaded("test.png");
    }

    @Test
    @Story("Checkbox")
    void selectCheckbox() {
        playGroundFormsSteps
                .selectCheckbox()
                .verifyCheckboxSelected();
    }

    @Test
    @Story("Radio buttons")
    void selectMaleRadioButton() {
        playGroundFormsSteps
                .selectMaleRadio()
                .verifyMaleRadioButtonSelected();
    }

    @Test
    @Story("Radio buttons")
    void selectFemaleRadioButton() {
        playGroundFormsSteps
                .selectFemaleRadio()
                .verifyFemaleRadioButtonSelected();
    }

}
