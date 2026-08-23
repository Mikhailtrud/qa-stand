package tests.playGroundTests;

import config.AuthenticatedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class FormsTests extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundFormsSteps.openPlayGroundPage().verifyPlaygroundTabsVisible();
    }

    @Test
    void fillTextInputTest() {
        playGroundFormsSteps
                .enterText("My text")
                .verifyTextInputValue("My text");
    }

    @Test
    void fillTextareaTest() {
        playGroundFormsSteps
                .enterTextarea("My textarea")
                .verifyTextareaValue("My textarea");
    }

    @Test
    void fillDateTest() {
        playGroundFormsSteps
                .enterDate("20.02.1988")
                .verifyDateValue("1988-02-20");
    }

    @Test
    void selectOptionTest() {
        playGroundFormsSteps
                .selectOption(2)
                .verifyOptionValue("Option 2");
    }

    @Test
    void selectMultioptionTest() {
        playGroundFormsSteps
                .selectMultiOption(1, 2, 3)
                .verifySelectedOptions("Kotlin", "Scala", "Groovy");
    }

    @Test
    void uploadFileTest() {
        Path file = Path.of("src/test/resources/files/test.png");

        playGroundFormsSteps
                .uploadFile(file)
                .verifyFileUploaded("test.png");
    }

    @Test
    void selectCheckbox() {
        playGroundFormsSteps
                .selectCheckbox()
                .verifyCheckboxSelected();
    }

    @Test
    void selectCMaleRadioButton() {
        playGroundFormsSteps
                .getRadioMale()
                .verifyMaleRadioButtonSelected();
    }

    @Test
    void selectCFemaleRadioButton() {
        playGroundFormsSteps
                .getRadioFemale()
                .verifyFemaleRadioButtonSelected();
    }

}
