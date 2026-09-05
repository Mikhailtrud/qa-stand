package pages;

import io.appium.java_client.AppiumBy;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public final class PlaygroundTablePage extends BasePage {
    private final By playgroundScreen = tag("playground_screen");
    private final By tableSearch = tag("table_search");
    private final By roleFilter = tag("role_filter");
    private final By tableNextButton = tag("next_page_button");
    private final By filterRolePanel = By.id("android:id/content");
    private final By tablePreviousButton = tag("previous_page_button");
    private final By sortId = tag("sort_id");
    private final By sortName = tag("sort_name");
    private final By sortRole = tag("sort_role");

    public void scrollToTable() {
        swipeUpToElement(tableNextButton);
    }

    public void focusTableSearch() {
        clickable(tableSearch).click();
    }

    public void enterTableSearch(String value) {
        type(tableSearch, value);
        getDriver().hideKeyboard();
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

        List<WebElement> rows = getDriver().findElements(AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionStartsWith(\"table_row_\")"
        ));
        List<String> values = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.className("android.widget.TextView"));
            values.add(cells.get(columnIndex).getText());
        }
        return values;
    }

    public void openRoleFilter() {
        clickable(roleFilter).click();
        visible(roleFilter("all"));
    }

    public void selectRoleFilter(String role) {
        scrollToElement(filterRolePanel, roleFilter(role)).click();
    }

    private By roleFilter(String role) {
        return tag("role_filter_" + role.toLowerCase());
    }

    public void openNextTablePage() {
        clickable(tableNextButton).click();
    }

    public void openPreviousTablePage() {
        clickable(tablePreviousButton).click();
    }

    public void sortById() {
        clickable(sortId).click();
    }

    public void sortByName() {
        clickable(sortName).click();
    }

    public void sortByRole() {
        clickable(sortRole).click();
    }

    public boolean isDisplayed() {
        return isDisplayed(playgroundScreen);
    }
}
