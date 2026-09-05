package pages;

import framework.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class PlaygroundFormsPage extends BasePage {
    private final By playgroundScreen = tag("playground_screen");
    private final By textInput = tag("playground_text_input");
    private final By textarea = tag("playground_textarea");
    private final By chooseDate = tag("playground_date");
    private final By datepicker = By.id("android:id/date_picker_header");
    private final By previousMonthButton = By.id("android:id/prev");
    private final By datePickerOkButton = By.id("android:id/button1");
    private final By selectYearButton = By.id("android:id/date_picker_header_year");
    private final By optionsSelect = tag("playground_select");
    private final By multiSelect = tag("playground_multiselect");
    private final By multiSelectJava = tag("multiselect_java");
    private final By chooseFileButton = tag("playground_file_upload");
    private final By selectedFileName = tag("selected_file_name");
    private final By checkbox = tag("playground_checkbox");
    private final By checkboxState = By.xpath(
            "//*[@content-desc='playground_checkbox']/parent::*[@checkable='true']"
    );

    public PlaygroundFormsPage enterTextInput(String value) {
        type(textInput, value);
        return this;
    }

    public String getTextInputValue() {
        return getDriver().switchTo().activeElement().getAttribute("text");
    }

    public PlaygroundFormsPage enterTextarea(String value) {
        type(textarea, value);
        return this;
    }

    public String getTextareaValue() {
        return getDriver().switchTo().activeElement().getAttribute("text");
    }

    public PlaygroundFormsPage selectDate(int year, int day) {
        openDatePicker();
        selectYear(year);
        selectPreviousMonth();
        selectDay(day);
        confirmDate();
        return this;
    }

    public String getSelectedDate(String expectedDate) {
        return visible(dateValue(expectedDate)).getText();
    }

    private void openDatePicker() {
        clickable(chooseDate).click();
        visible(datepicker);
    }

    private void selectYear(int year) {
        clickable(selectYearButton).click();
        visible(datepicker);
        clickable(year(year)).click();
    }

    private void selectPreviousMonth() {
        clickable(previousMonthButton).click();
    }

    private void selectDay(int day) {
        clickable(day(day)).click();
    }

    private void confirmDate() {
        clickable(datePickerOkButton).click();
    }

    private By year(int year) {
        return By.xpath("//*[@text='" + year + "']");
    }

    private By day(int day) {
        return By.xpath("//*[@text='" + day + "']");
    }

    private By dateValue(String date) {
        return AppiumBy.androidUIAutomator("new UiSelector().text(\"" + date + "\")");
    }

    public void openSelect() {
        clickable(optionsSelect).click();
    }

    public boolean isOptionDisplayed(int number) {
        return isDisplayed(option(number));
    }

    public void selectOption(int number) {
        clickable(option(number)).click();
    }

    public String getDisplayedOptionText(String expectedOption) {
        return visible(verifyTextEquals(expectedOption)).getText();
    }

    private By option(int number) {
        return tag("select_option_" + number);
    }

    private By verifyTextEquals(String optionText) {
        return AppiumBy.androidUIAutomator("new UiSelector().text(\"" + optionText + "\")");
    }

    public void openMultiSelect() {
        clickable(multiSelect).click();
        visible(multiSelectJava);
    }

    public void selectMultiOption(String option) {
        clickable(multiOption(option)).click();
    }

    public boolean isOptionSelected(String option) {
        return Boolean.parseBoolean(visible(multiOption(option)).getAttribute("checked"));
    }

    public PlaygroundFormsPage closeMultiSelect() {
        DriverManager.getDriver().navigate().back();
        visible(multiSelect);
        return this;
    }

    private By multiOption(String option) {
        return tag("multiselect_" + option);
    }

    public void openFileChooser() {
        clickable(chooseFileButton).click();
    }

    public String getSelectedFileName() {
        return visible(selectedFileName).getText();
    }

    public PlaygroundFormsPage selectFile(String fileName) {
        clickable(file(fileName)).click();
        return this;
    }

    private By file(String fileName) {
        return AppiumBy.androidUIAutomator("new UiSelector().text(\"" + fileName + "\")");
    }

    public void toggleAcceptTerms() {
        clickable(checkbox).click();
    }

    public boolean isAcceptTermsChecked() {
        return Boolean.parseBoolean(visible(checkboxState).getAttribute("checked"));
    }

    public PlaygroundFormsPage selectRadio(String option) {
        clickable(radio(option)).click();
        return this;
    }

    public boolean isRadioChecked(String option) {
        return Boolean.parseBoolean(visible(radio(option)).getAttribute("checked"));
    }

    private By radio(String option) {
        return tag("radio_" + option);
    }

    public boolean isDisplayed() {
        return isDisplayed(playgroundScreen);
    }
}
