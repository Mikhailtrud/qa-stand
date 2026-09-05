package steps;

import io.qameta.allure.Step;
import pages.PlaygroundFormsPage;

import static org.assertj.core.api.Assertions.assertThat;

public final class PlaygroundFormsSteps extends BaseSteps {
    private final PlaygroundFormsPage playgroundFormsPage = new PlaygroundFormsPage();

    @Step("Verify QA Playground is displayed")
    public PlaygroundFormsSteps verifyPlaygroundDisplayed() {
        assertThat(playgroundFormsPage.isDisplayed()).isTrue();
        return this;
    }

    @Step("Fill forms input")
    public PlaygroundFormsSteps fillInput(String text) {
        playgroundFormsPage.enterTextInput(text);
        return this;
    }

    @Step("Fill forms textarea")
    public PlaygroundFormsSteps fillTextarea(String text) {
        playgroundFormsPage.enterTextarea(text);
        return this;
    }

    @Step("Verify forms input value: {expectedText}")
    public PlaygroundFormsSteps verifyInputValue(String expectedText) {
        assertThat(playgroundFormsPage.getTextInputValue()).isEqualTo(expectedText);
        return this;
    }

    @Step("Verify forms textarea value: {expectedText}")
    public PlaygroundFormsSteps verifyTextareaValue(String expectedText) {
        assertThat(playgroundFormsPage.getTextareaValue()).isEqualTo(expectedText);
        return this;
    }

    @Step("Select day {day} in the previous month of {year}")
    public PlaygroundFormsSteps selectDate(int year, int day) {
        playgroundFormsPage.selectDate(year, day);
        return this;
    }

    @Step("Verify selected date: {expectedDate}")
    public PlaygroundFormsSteps verifySelectedDate(String expectedDate) {
        assertThat(playgroundFormsPage.getSelectedDate(expectedDate)).isEqualTo(expectedDate);
        return this;
    }

    @Step("Open the options select")
    public PlaygroundFormsSteps openSelect() {
        playgroundFormsPage.openSelect();
        return this;
    }

    @Step("Verify option {number} visible")
    public PlaygroundFormsSteps verifyOptionDisplayed(int number) {
        assertThat(playgroundFormsPage.isOptionDisplayed(number)).isTrue();
        return this;
    }

    @Step("Select option {number}")
    public PlaygroundFormsSteps selectOption(int number) {
        playgroundFormsPage.selectOption(number);
        return this;
    }

    @Step("Verify selected option")
    public PlaygroundFormsSteps verifyOptionSelected(String expectedOption) {
        assertThat(playgroundFormsPage.getDisplayedOptionText(expectedOption)).isEqualTo(expectedOption);
        return this;
    }

    @Step("Open multi select")
    public PlaygroundFormsSteps openMultiSelect() {
        playgroundFormsPage.openMultiSelect();
        return this;
    }

    @Step("Select multi-select options: {options}")
    public PlaygroundFormsSteps selectMultiOptions(String... options) {
        for (String option : options) {
            playgroundFormsPage.selectMultiOption(option);
        }
        return this;
    }

    @Step("Verify selected options: {options}")
    public PlaygroundFormsSteps verifyOptionsSelected(String... options) {
        for (String option : options) {
            assertThat(playgroundFormsPage.isOptionSelected(option))
                    .as("Option should be selected: " + option)
                    .isTrue();
        }
        return this;
    }

    @Step("Verify multi-select summary: {expectedOption}")
    public PlaygroundFormsSteps verifyMultiSelectSummary(String expectedOption) {
        assertThat(playgroundFormsPage.getDisplayedOptionText(expectedOption)).isEqualTo(expectedOption);
        return this;
    }

    @Step("Close multi select")
    public PlaygroundFormsSteps closeMultiSelect() {
        playgroundFormsPage.closeMultiSelect();
        return this;
    }

    @Step("Open file chooser")
    public PlaygroundFormsSteps openFileChooser() {
        playgroundFormsPage.openFileChooser();
        return this;
    }

    @Step("Choose file {fileName}")
    public PlaygroundFormsSteps chooseFile(String fileName) {
        playgroundFormsPage.selectFile(fileName);
        return this;
    }

    @Step("Verify a file is selected")
    public PlaygroundFormsSteps verifyFileSelected() {
        assertThat(playgroundFormsPage.getSelectedFileName())
                .isNotBlank()
                .isNotEqualTo("No file selected");
        return this;
    }

    @Step("Toggle Accept Terms")
    public PlaygroundFormsSteps toggleAcceptTerms() {
        playgroundFormsPage.toggleAcceptTerms();
        return this;
    }

    @Step("Verify Accept Terms checked state is {expectedState}")
    public PlaygroundFormsSteps verifyAcceptTermsState(boolean expectedState) {
        assertThat(playgroundFormsPage.isAcceptTermsChecked()).isEqualTo(expectedState);
        return this;
    }

    @Step("Select radio button: {option}")
    public PlaygroundFormsSteps selectRadio(String option) {
        playgroundFormsPage.selectRadio(option);
        return this;
    }

    @Step("Verify radio button {option} checked state is {expectedState}")
    public PlaygroundFormsSteps verifyRadioState(String option, boolean expectedState) {
        assertThat(playgroundFormsPage.isRadioChecked(option)).isEqualTo(expectedState);
        return this;
    }
}
