package pages;

import framework.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PlaygroundPage extends BasePage {

    private final By playgroundScreen  = tag("playground_screen");
    private final By usersButton = tag("open_users_button");

    //Forms input
    private final By textInput = tag("playground_text_input");

    public PlaygroundPage enterTextInput(String value) {
        type(textInput, value);
        return this;
    }

    //Forms textarea
    private final By textarea = tag("playground_textarea");

    public PlaygroundPage enterTextTextarea(String value) {
        type(textarea, value);
        return this;
    }

    //Forms Datepicker
    private final By chooseDate = tag("playground_date");
    private final By datepicker = By.id("android:id/date_picker_header");
    private final By nextMonthButton = By.id("android:id/next");
    private final By previousMonthButton = By.id("android:id/prev");
    private final By datePickerCancelButton  = By.id("android:id/button2");
    private final By datePickerOkButton  = By.id("android:id/button1");
    private final By selectYearButton  = By.id("android:id/date_picker_header_year");
    private By year(int year) { return By.xpath("//*[@text='" + year + "']"); }
    private By day(int day) { return By.xpath("//*[@text='" + day + "']"); }

    public PlaygroundPage selectDate(int year, int day) {
        openDatePicker();
        selectYear(year);
        selectPreviousMonth();
        selectDay(day);
        confirmDate();

        return this;
    }

    public PlaygroundPage verifySelectedDate(String expectedDate) {
        verifyText(dateValue(expectedDate), expectedDate);
        return this;
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

    private By dateValue(String date) {
        return AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + date + "\")"
        );
    }

    //Forms Options select
    private final By optionsSelect = tag("playground_select");

    private By option(int number) {
        return tag("select_option_" + number);
    }

    public void clickOnOptionSelectElement() {
        clickable(optionsSelect).click();
    }

    public boolean verifyOptionVisible(int number) {
        return isDisplayed(option(number));
    }

    public void selectOption(int number) {
        clickable(option(number)).click();
    }

    private By verifyTextEquals(String optionText) {
        return AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + optionText + "\")"
        );
    }

    public PlaygroundPage verifyText(String expectedOption) {
        verifyText(verifyTextEquals(expectedOption), expectedOption);
        return this;
    }

    //Forms Multi select
    private final By multiSelect = tag("playground_multiselect");
    private final By multiSelectJava = tag("multiselect_java");

    public void clickOnMultiSelectElement() {
        clickable(multiSelect).click();
        visible(multiSelectJava);
    }

    private By multiOption(String option) {
        return tag("multiselect_" + option);
    }

    public void selectOptions(String option) {
            clickable(multiOption(option)).click();
    }

    public boolean isOptionSelected(String option) {
        return Boolean.parseBoolean(
                visible(multiOption(option)).getAttribute("checked")
        );
    }

    public PlaygroundPage closeMultiSelect() {
        DriverManager.getDriver().navigate().back();
        visible(multiSelect);
        return this;
    }

    //Forms Upload file
    private final By chooseFileButton = tag("playground_file_upload");
    private final By selectedFileName = tag("selected_file_name");

    public void clickOnSelectFileElement() {
        clickable(chooseFileButton).click();
    }

    public String getSelectedFileName() {
        return visible(selectedFileName).getText();
    }

    private By file(String fileName) {
        return AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + fileName + "\")"
        );
    }

    public PlaygroundPage selectFile(String fileName) {
        clickable(file(fileName)).click();
        return this;
    }


    //Forms Checkbox
    private final By checkbox = tag("playground_checkbox");
    private final By acceptTerms = By.xpath("//*[@content-desc='playground_checkbox']/..");

    public void toggleAcceptTerms() {
        clickable(checkbox).click();
    }

    public boolean isAcceptTermsChecked() {
        return Boolean.parseBoolean(
                visible(acceptTerms).getAttribute("checked")
        );
    }

    //Forms Radio buttons
    private By radio(String option) {
        return tag("radio_" + option);
    }

    public PlaygroundPage selectRadio(String option) {
        clickable(radio(option)).click();
        return this;
    }

    public boolean isRadioChecked(String option) {
        return Boolean.parseBoolean(
                visible(radio(option)).getAttribute("checked")
        );
    }

    //JavaScript
    //JavaScript Alert
    private final By alertButton = tag("playground_alert_button");
    private final By alertDialog = tag("alert_dialog");
    private final By okAlertButton = tag("alert_dialog_close_button");

    public void verifyAlertWindowVisible() {
        visible(alertDialog);
    }

    public void closeAlert() {
        clickable(okAlertButton).click();
    }

    public void openAlert() {
        scrollToElement(playgroundScreen, alertButton).click();
    }

    //JavaScript Confirm
    private final By confirmButton = tag("playground_confirm_button");
    private final By confirmDialog = tag("confirm_dialog");
    private final By okConfirmButton = tag("confirm_dialog_close_button");
    private final By cancelConfirmButton = tag("confirm_dialog_cancel_button");

    public void openConfirm() {
        scrollToElement(playgroundScreen, confirmButton).click();
    }

    public void verifyConfirmWindowVisible() {
        visible(confirmDialog);
    }

    public void clickOnOkConfirmButton() {
        clickable(okConfirmButton).click();
    }

    public void cancelConfirm() {
        clickable(cancelConfirmButton).click();
    }

    //JavaScript Prompt
    private final By promptButton = tag("playground_prompt_button");
    private final By promptDialog = tag("prompt_dialog");
    private final By promptInput = tag("prompt_input");
    private final By promptOkButton = tag("prompt_ok_button");
    private final By promptCancelButton = tag("prompt_cancel_button");

    public void openPrompt() {
        scrollToElement(playgroundScreen, promptButton).click();
    }

    public void verifyPromptWindowVisible() {
        visible(promptDialog);
    }

    public void clickOnOkPromptButton() {
        clickable(promptOkButton).click();
    }

    public void cancelCPrompt() {
        clickable(promptCancelButton).click();
    }

    public void enterTextPromptInput(String value) {
        type(promptInput, value);
    }

    //JavaScript Toast
    private final By toastButton = tag("playground_toast_button");

    public void openToast() {
        scrollToElement(playgroundScreen, toastButton).click();
    }

    private final By toastMessage = tag("toast_message");

    public String getToastText() {
        return visible(toastMessage).getText();
    }

    //JavaScript Modal window
    private final By modalButton = tag("open_modal_button");
    private final By modalWindow = tag("modal_window");
    private final By modalWindowCloseButton = tag("modal_window_close_button");

    public void verifyAModalWindowVisible() {
        visible(modalWindow);
    }

    public void closeModal() {
        clickable(modalWindowCloseButton).click();
    }

    public void openModal() {
        scrollToElement(playgroundScreen, modalButton).click();
    }

    //Tabs
    private final By tabContent = tag("tab_content");

    private By tabs(int tab) {
        return tag("tab_" + tab + "_button");
    }

    public void selectTab(int tab) {
        scrollToElement(playgroundScreen, tabs(tab)).click();
    }

    public void verifyTabContent(String expectedText) {
        verifyText(tabContent, expectedText);
    }


    //Table
    private final By tableSearch = tag("table_search");
    private final By roleFilter = tag("role_filter");
    private final By table = tag("dynamic_table");
    private final By tableNextButton = tag("next_page_button");
    private final By filterRolePanel = By.id("android:id/content");
    private final By tablePreviousButton = tag("previous_page_button");
    private final By currentPage = tag("current_page");
    private final By sortId = tag("sort_id");
    private final By sortName = tag("sort_name");
    private final By sortRole = tag("sort_role");

    public void scrollToTable() {
        swipeUpToElement(tableNextButton);
    }

    public boolean isTableDisplayed() {
        return isDisplayed(table);
    }

    public void tableSearchClick() {
        clickable(tableSearch).click();
    }

    public void tableSearchFillText(String value) {
        type(tableSearch, value);
    }

    private By tableName(String name) {
        return AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + name + "\")"
        );
    }

    public void verifyNameAfterSearch(String expectedText) {
        verifyText(tableName(expectedText), expectedText);
    }

    public List<String> getTableRowValues(int id) {
        WebElement row = visible(tag("table_row_" + id));

        return row.findElements(By.className("android.widget.TextView"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getTableColumnValues(String columnName) {
        List<String> columns = List.of("ID", "Name", "Role");

        int columnIndex = columns.indexOf(columnName);

        if (columnIndex == -1) {
            throw new NoSuchElementException("Column not found: " + columnName);
        }

        List<WebElement> rows = getDriver()
                .findElements(AppiumBy.androidUIAutomator(
                        "new UiSelector().descriptionStartsWith(\"table_row_\")"
                ));

        List<String> values = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells =
                    row.findElements(By.className("android.widget.TextView"));

            values.add(cells.get(columnIndex).getText());
        }

        return values;
    }

    public void openFilterClick() {
        clickable(roleFilter).click();
        visible(roleFilter("all"));
    }

    private By roleFilter(String role) {
        return tag("role_filter_" + role.toLowerCase() );
    }

    public void selectRoleFilter(String role) {
        scrollToElement(filterRolePanel, roleFilter(role)).click();
    }

    public void tableNextButtonClick() {
        clickable(tableNextButton).click();
    }

    public void tablePreviousButtonClick() {
        clickable(tablePreviousButton).click();
    }

    public void sortByIdClick() {
        clickable(sortId).click();
    }

    public void sortByNameClick() {
        clickable(sortName).click();
    }

    public void sortByRoleClick() {
        clickable(sortRole).click();
    }

    //Dynamic Elements
    private final By startLoaderButton = tag("start_loader_button");
    private final By showDelayedButton = tag("show_delayed_button");
    private final By delayedButton = tag("delayed_button");
    private final By hiddenElementText = tag("hidden_element");

    public void scrollToDawn() {
        swipeUpToElement(hoverButton);
    }

    public void startLoaderButtonClick() {
        clickable(startLoaderButton).click();
    }

    public void showDelayedButtonClick() {
        clickable(showDelayedButton).click();
    }

    public void delayedButtonVisible() {
        visible(delayedButton);
    }

    public boolean isLoaderDisplayed() {
        return Objects.requireNonNull(getDriver()
                        .getPageSource())
                .contains("Loading...");
    }

    public boolean isHiddenElementDisplayed(String expectedText) {
        return Objects.requireNonNull(DriverManager.getDriver()
                        .getPageSource())
                .contains(expectedText);
    }

    //Mouse Actions
    private final By hoverButton = tag("hover_button");
    private final By doubleClickButton = tag("double_click_button");
    private final By rightClickButton = tag("right_click_button");
    private final By mouseActionResult = tag("mouse_action_result");

    public void tapHoverButton() {
        clickable(hoverButton).click();
    }

    public void doubleTapButton() {
        RemoteWebElement button = (RemoteWebElement) clickable(doubleClickButton);
        getDriver().executeScript(
                "mobile: doubleClickGesture",
                Map.of("elementId", button.getId())
        );
    }

    public void longPressButton() {
        RemoteWebElement button = (RemoteWebElement) clickable(rightClickButton);
        getDriver().executeScript(
                "mobile: longClickGesture",
                Map.of(
                        "elementId", button.getId(),
                        "duration", 1000
                )
        );
    }

    public String getMouseActionResult() {
        return visible(mouseActionResult).getText();
    }

    public boolean isDisplayed() {
        return isDisplayed(playgroundScreen);
    }

    public void openUsers() {
        clickable(usersButton).click();
    }

}
