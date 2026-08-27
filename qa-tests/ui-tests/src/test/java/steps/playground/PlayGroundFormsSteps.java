package steps.playground;

import com.codeborne.selenide.Condition;
import steps.BaseSteps;
import io.qameta.allure.Step;

import java.nio.file.Path;

public class PlayGroundFormsSteps extends BaseSteps {

    @Step("Open the Playground page")
    public PlayGroundFormsSteps openPlayGroundPage() {
        playGroundPage.open();
        return this;
    }

    @Step("Verify that the Forms section is visible")
    public PlayGroundFormsSteps verifyPlaygroundFormsVisible() {
        playGroundPage.formsBlock().shouldBe(Condition.visible);
        return this;
    }

    @Step("Enter text: {text}")
    public PlayGroundFormsSteps enterText(String text) {
        playGroundFormsPage.enterText(text);
        return this;
    }

    @Step("Verify text input value: {expectedText}")
    public PlayGroundFormsSteps verifyTextInputValue(String expectedText) {
        playGroundFormsPage.verifyTextInputValue(expectedText);
        return this;
    }

    @Step("Enter textarea value: {text}")
    public PlayGroundFormsSteps enterTextarea(String text) {
        playGroundFormsPage.enterTextarea(text);
        return this;
    }

    @Step("Verify textarea value: {expectedText}")
    public PlayGroundFormsSteps verifyTextareaValue(String expectedText) {
        playGroundFormsPage.verifyTextareaValue(expectedText);
        return this;
    }

    @Step("Enter date: {text}")
    public PlayGroundFormsSteps enterDate(String text) {
        playGroundFormsPage.enterDate(text);
        return this;
    }

    @Step("Verify date value: {date}")
    public PlayGroundFormsSteps verifyDateValue(String date) {
        playGroundFormsPage.verifyDateValue(date);
        return this;
    }

    @Step("Select option with index {option}")
    public PlayGroundFormsSteps selectOption(int option) {
        playGroundFormsPage.selectOption(option);
        return this;
    }

    @Step("Verify that option with index {option} is visible")
    public PlayGroundFormsSteps verifyOptionVisible(int option) {
        playGroundFormsPage.verifyOptionVisible(option);
        return this;
    }

    @Step("Verify selected option: {option}")
    public PlayGroundFormsSteps verifyOptionValue(String option) {
        playGroundFormsPage.verifyOptionValue(option);
        return this;
    }

    @Step("Select multiple options")
    public PlayGroundFormsSteps selectMultiOption(int... index) {
        playGroundFormsPage.selectMultiSelectOptions(index);
        return this;
    }

    @Step("Verify selected options")
    public PlayGroundFormsSteps verifySelectedOptions(String... expectedOptions) {
        playGroundFormsPage.verifySelectedOptions(expectedOptions);
        return this;
    }

    @Step("Upload file: {path}")
    public PlayGroundFormsSteps uploadFile(Path path) {
        playGroundFormsPage.uploadFile(path);
        return this;
    }

    @Step("Verify uploaded file name: {fileName}")
    public PlayGroundFormsSteps verifyFileUploaded(String fileName) {
        playGroundFormsPage.verifyFileUploaded(fileName);
        return this;
    }

    @Step("Select the checkbox")
    public PlayGroundFormsSteps selectCheckbox() {
        playGroundFormsPage.checkbox().click();
        return this;
    }

    @Step("Verify that the checkbox is selected")
    public PlayGroundFormsSteps verifyCheckboxSelected() {
        playGroundFormsPage.verifyCheckboxSelected();
        return this;
    }

    @Step("Select the Male radio button")
    public PlayGroundFormsSteps selectMaleRadio() {
        playGroundFormsPage.selectMaleRadio();
        return this;
    }

    @Step("Verify that the Male radio button is selected")
    public PlayGroundFormsSteps verifyMaleRadioButtonSelected() {
        playGroundFormsPage.verifyMaleRadioButtonSelected();
        return this;
    }

    @Step("Select the Female radio button")
    public PlayGroundFormsSteps selectFemaleRadio() {
        playGroundFormsPage.selectFemaleRadio();
        return this;
    }

    @Step("Verify that the Female radio button is selected")
    public PlayGroundFormsSteps verifyFemaleRadioButtonSelected() {
        playGroundFormsPage.verifyFemaleRadioButtonSelected();
        return this;
    }
}
