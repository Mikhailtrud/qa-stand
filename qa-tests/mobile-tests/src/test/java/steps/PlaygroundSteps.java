package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import pages.PlaygroundPage;
import pages.UsersPage;

import java.util.List;

public final class PlaygroundSteps extends BaseSteps {
    private final UsersPage usersPage = new UsersPage();
    private final PlaygroundPage playgroundPage = new PlaygroundPage();

    @Step("Open QA Playground")
    public PlaygroundSteps openPlayground() {
        usersPage.openPlayground();
        return this;
    }

    @Step("Verify QA Playground is displayed")
    public PlaygroundSteps verifyPlaygroundDisplayed() {
        assertThat(playgroundPage.isDisplayed()).isTrue();
        return this;
    }

    @Step("Fill forms input")
    public PlaygroundSteps fillInput(String text) {
        playgroundPage.enterTextInput(text);
        return this;
    }

    @Step("Fill forms textarea")
    public PlaygroundSteps fillTextarea(String text) {
        playgroundPage.enterTextTextarea(text);
        return this;
    }

    //Datepicker
    @Step("Select date {day}.{month}.{year}")
    public PlaygroundSteps selectDate(int year, int day) {
        playgroundPage.selectDate(year, day);
        return this;
    }

    @Step("Verify selected date: {expectedDate}")
    public PlaygroundSteps verifySelectedDate(String expectedDate) {
        playgroundPage.verifySelectedDate(expectedDate);
        return this;
    }

    //Forms Options select
    @Step("Select date option")
    public PlaygroundSteps selectOption() {
        playgroundPage.clickOnOptionSelectElement();
        return this;
    }

    @Step("Verify option {number} visible")
    public PlaygroundSteps verifySaveButtonEnabled(int number) {
        assertThat(playgroundPage.verifyOptionVisible(number)).isTrue();
        return this;
    }

    @Step("Select option {number}")
    public PlaygroundSteps selectOption(int number) {
        playgroundPage.selectOption(number);
        return this;
    }

    @Step("Verify selected option")
    public PlaygroundSteps verifyOptionSelected(String expectedOption) {
        playgroundPage.verifyText(expectedOption);
        return this;
    }

    //Forms Multi select
    @Step("Open multi select")
    public PlaygroundSteps openMultiSelect() {
        playgroundPage.clickOnMultiSelectElement();
        return this;
    }

    @Step("Select options")
    public PlaygroundSteps selectOptions(String... options) {
        for (String option : options) {
            playgroundPage.selectOptions(option);
        }
        return this;
    }

    @Step("Verify selected options: {options}")
    public PlaygroundSteps verifyOptionsSelected(String... options) {
        for (String option : options) {
            assertThat(playgroundPage.isOptionSelected(option))
                    .as("Option should be selected: " + option)
                    .isTrue();
        }
        return this;
    }

    @Step("Verify selected multi option")
    public PlaygroundSteps verifyMultiOptionSelected(String expectedOption) {
        playgroundPage.verifyText(expectedOption);
        return this;
    }

    @Step("Close multi select")
    public PlaygroundSteps closeMultiSelect() {
        playgroundPage.closeMultiSelect();
        return this;
    }

    //Forms Upload file
    @Step("Open file chooser")
    public PlaygroundSteps openChooseFile() {
        playgroundPage.clickOnSelectFileElement();
        return this;
    }

    @Step("Choose file {fileName}")
    public PlaygroundSteps chooseFile(String fileName) {
        playgroundPage.selectFile(fileName);
        return this;
    }

    @Step("Verify file was selected")
    public PlaygroundSteps verifyFileSelected() {
        assertThat(playgroundPage.getSelectedFileName())
                .isNotEqualTo("No file selected");
        return this;
    }

    //Forms Checkbox
    @Step("Select Accept Terms")
    public PlaygroundSteps selectAcceptTerms() {
        playgroundPage.toggleAcceptTerms();
        return this;
    }

    @Step("Verify Accept Terms checked state is {expectedState}")
    public PlaygroundSteps verifyAcceptTermsState(boolean expectedState) {
        assertThat(playgroundPage.isAcceptTermsChecked())
                .isEqualTo(expectedState);
        return this;
    }

    //Forms Radio buttons
    @Step("Select radio button: {option}")
    public PlaygroundSteps selectRadio(String option) {
        playgroundPage.selectRadio(option);
        return this;
    }

    @Step("Verify radio button {option} checked state is {expectedState}")
    public PlaygroundSteps verifyRadioState(String option, boolean expectedState) {
        assertThat(playgroundPage.isRadioChecked(option))
                .isEqualTo(expectedState);
        return this;
    }

    //JavaScript
    //Alert
    @Step("Open alert")
    public PlaygroundSteps openAlert() {
        playgroundPage.openAlert();
        return this;
    }

    @Step("Verify alert window visible")
    public PlaygroundSteps verifyAlertWindowVisible() {
        playgroundPage.verifyAlertWindowVisible();
        return this;
    }

    @Step("Close alert")
    public PlaygroundSteps closeAlert() {
        playgroundPage.closeAlert();
        return this;
    }

    //Confirm
    @Step("Open confirm")
    public PlaygroundSteps openConfirm() {
        playgroundPage.openConfirm();
        return this;
    }

    @Step("Verify confirm window visible")
    public PlaygroundSteps verifyConfirmWindowVisible() {
        playgroundPage.verifyConfirmWindowVisible();
        return this;
    }

    @Step("Click on OK button on confirm window")
    public PlaygroundSteps clickOnOkConfirmButton() {
        playgroundPage.clickOnOkConfirmButton();
        return this;
    }

    @Step("Click on OK button on confirm window")
    public PlaygroundSteps cancelConfirm() {
        playgroundPage.cancelConfirm();
        return this;
    }

    //Prompt
    @Step("Open prompt")
    public PlaygroundSteps openPrompt() {
        playgroundPage.openPrompt();
        return this;
    }

    @Step("Fill forms prompt input")
    public PlaygroundSteps fillPromptInput(String text) {
        playgroundPage.enterTextPromptInput(text);
        return this;
    }

    @Step("Verify prompt window visible")
    public PlaygroundSteps verifyPromptWindowVisible() {
        playgroundPage.verifyPromptWindowVisible();
        return this;
    }

    @Step("Click on OK button on prompt window")
    public PlaygroundSteps clickOnOkPromptButton() {
        playgroundPage.clickOnOkPromptButton();
        return this;
    }

    @Step("Click on OK button on prompt window")
    public PlaygroundSteps cancelPrompt() {
        playgroundPage.cancelCPrompt();
        return this;
    }

    //Toast
    @Step("Open toast")
    public PlaygroundSteps openToast() {
        playgroundPage.openToast();
        return this;
    }

    @Step("Verify toast text: {expectedText}")
    public PlaygroundSteps verifyToast(String expectedText) {
        assertThat(playgroundPage.isToastDisplayed(expectedText))
                .isTrue();
        return this;
    }

    //Modal
    @Step("Open modal window")
    public PlaygroundSteps openModal() {
        playgroundPage.openModal();
        return this;
    }

    @Step("Verify modal window visible")
    public PlaygroundSteps verifyAModalWindowVisible() {
        playgroundPage.verifyAModalWindowVisible();
        return this;
    }

    @Step("Close modal window")
    public PlaygroundSteps closeModal() {
        playgroundPage.closeModal();
        return this;
    }

    //Tabs
    @Step("Close modal window")
    public PlaygroundSteps selectTabs(int tab) {
        playgroundPage.selectTab(tab);
        return this;
    }

    @Step("Verify tab content: {expectedText}")
    public PlaygroundSteps verifyTabContent(String expectedText) {
        playgroundPage.verifyTabContent(expectedText);
        return this;
    }

    //Table
    @Step("Close modal window")
    public PlaygroundSteps scrollToTable() {
        playgroundPage.scrollToTable();
        return this;
    }

    @Step("Click on search field")
    public PlaygroundSteps tableSearchClick() {
        playgroundPage.tableSearchClick();
        return this;
    }

    @Step("Fill forms search input")
    public PlaygroundSteps tableSearchFillText(String text) {
        playgroundPage.tableSearchFillText(text);
        return this;
    }

    @Step("Verify name {name}")
    public PlaygroundSteps verifyNameAfterSearch(String name) {
        playgroundPage.verifyNameAfterSearch(name);
        return this;
    }

    @Step("Verify table row: {id}, {name}, {role}")
    public PlaygroundSteps verifyTableRow(int id, String name, String role) {
        assertThat(playgroundPage.getTableRowValues(id))
                .containsExactly(
                        String.valueOf(id),
                        name,
                        role
                );

        return this;
    }

    @Step("Open role filter")
    public PlaygroundSteps openFilterClick() {
        playgroundPage.openFilterClick();
        return this;
    }

    @Step("Click on role filter")
    public PlaygroundSteps roleFilterClick(String role) {
        playgroundPage.selectRoleFilter(role);
        return this;
    }

    @Step("Verify column {columnName} values")
    public PlaygroundSteps verifyColumnValues(
            String columnName,
            String... expectedValues
    ) {
        assertThat(playgroundPage.getTableColumnValues(columnName))
                .containsExactly(expectedValues);

        return this;
    }

    @Step("Open next page")
    public PlaygroundSteps tableNextButtonClick() {
        playgroundPage.tableNextButtonClick();
        return this;
    }

    @Step("Open previous page")
    public PlaygroundSteps tablePreviousButtonClick() {
        playgroundPage.tablePreviousButtonClick();
        return this;
    }

    @Step("Sort by ID")
    public PlaygroundSteps sortByIdClick() {
        playgroundPage.sortByIdClick();
        return this;
    }

    @Step("Sort by Name")
    public PlaygroundSteps sortByNameClick() {
        playgroundPage.sortByNameClick();
        return this;
    }

    @Step("Sort by Role")
    public PlaygroundSteps sortByRoleClick() {
        playgroundPage.sortByRoleClick();
        return this;
    }

    //Dynamic Elements
    @Step("Scroll dawn")
    public PlaygroundSteps scrollDawn() {
        playgroundPage.scrollToDawn();
        return this;
    }

    @Step("Close modal window")
    public PlaygroundSteps startLoader() {
        playgroundPage.startLoaderButtonClick();
        return this;
    }

    @Step("Verify loader is displayed")
    public PlaygroundSteps verifyLoaderDisplayed() {
        assertThat(playgroundPage.isLoaderDisplayed())
                .isTrue();
        return this;
    }

    @Step("Show delayed button")
    public PlaygroundSteps showDelayedButton() {
        playgroundPage.showDelayedButtonClick();
        return this;
    }

    @Step("Vrify delayed button displayed")
    public PlaygroundSteps delayedButtonVisible() {
        playgroundPage.delayedButtonVisible();
        return this;
    }

    @Step("Verify toast text: {expectedText}")
    public PlaygroundSteps verifyHiddenElementText(String expectedText) {
        assertThat(playgroundPage.isHiddenElementDisplayed(expectedText))
                .isTrue();
        return this;
    }

    //Mouse Actions
    @Step("Tap Hover action")
    public PlaygroundSteps tapHoverAction() {
        playgroundPage.tapHoverButton();
        return this;
    }

    @Step("Double tap action")
    public PlaygroundSteps doubleClickAction() {
        playgroundPage.doubleTapButton();
        return this;
    }

    @Step("Long press Right Click action")
    public PlaygroundSteps rightClickAction() {
        playgroundPage.longPressButton();
        return this;
    }

    @Step("Verify mouse action result: {expectedText}")
    public PlaygroundSteps verifyMouseActionResult(String expectedText) {
        assertThat(playgroundPage.getMouseActionResult())
                .isEqualTo(expectedText);
        return this;
    }
}
