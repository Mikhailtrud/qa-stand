package steps.playground;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import steps.BaseSteps;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayGroundTablesSteps extends BaseSteps {

    private List<String> rememberedPageRows;

    @Step("Open the Playground page")
    public PlayGroundTablesSteps openPlayGroundPage() {
        playGroundPage.open();
        return this;
    }

    @Step("Verify that the Tables section is visible")
    public PlayGroundTablesSteps verifyPlaygroundTablesVisible() {
        playGroundPage.tablesBlock().shouldBe(Condition.visible);
        return this;
    }

    @Step("Search the table for: {value}")
    public PlayGroundTablesSteps enterSearchValue(String value) {
        playGroundTablesPage.enterSearchValue(value);
        return this;
    }

    @Step("Verify table search results for: {searchText}")
    public PlayGroundTablesSteps verifySearchResults(String searchText) {
        List<String> rows = nonEmptyTexts(playGroundTablesPage.tableRows());
        assertFalse(
                rows.stream().anyMatch(row -> !row.toLowerCase().contains(searchText.toLowerCase())),
                "Every displayed row must match the search text"
        );
        return this;
    }

    @Step("Filter the table by role: {role}")
    public PlayGroundTablesSteps selectRoleFilter(String role) {
        playGroundTablesPage.selectRoleFilter(role);
        return this;
    }

    @Step("Verify that every displayed row has role {role}")
    public PlayGroundTablesSteps verifyAllRowsContainRole(String role) {
        List<String> roles = nonEmptyTexts(playGroundTablesPage.roleCells());
        assertEquals(roles.stream().map(ignored -> role).toList(), roles);
        return this;
    }

    @Step("Sort the table by ID")
    public PlayGroundTablesSteps sortById() {
        playGroundTablesPage.clickSortById();
        return this;
    }

    @Step("Verify ascending ID order")
    public PlayGroundTablesSteps verifyIdsSortedAscending() {
        List<Integer> ids = allIds();
        assertEquals(ids.stream().sorted().toList(), ids);
        return this;
    }

    @Step("Verify descending ID order")
    public PlayGroundTablesSteps verifyIdsSortedDescending() {
        List<Integer> ids = allIds();
        assertEquals(ids.stream().sorted(Comparator.reverseOrder()).toList(), ids);
        return this;
    }

    @Step("Sort the table by name")
    public PlayGroundTablesSteps sortByName() {
        playGroundTablesPage.clickSortByName();
        return this;
    }

    @Step("Verify ascending name order")
    public PlayGroundTablesSteps verifyNamesSortedAscending() {
        List<String> names = allTexts(playGroundTablesPage::nameCells);
        assertEquals(names.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList(), names);
        return this;
    }

    @Step("Verify that {expectedCount} rows are displayed")
    public PlayGroundTablesSteps verifyDisplayedRowsCount(int expectedCount) {
        playGroundTablesPage.tableRows().shouldHave(size(expectedCount));
        return this;
    }

    @Step("Verify that search results are visible")
    public PlayGroundTablesSteps verifySearchResultsVisible() {
        ElementsCollection rows = playGroundTablesPage.tableRows().shouldHave(sizeGreaterThan(0));
        rows.forEach(row -> row.shouldBe(visible));
        return this;
    }

    @Step("Sort the table by role")
    public PlayGroundTablesSteps sortByRole() {
        playGroundTablesPage.clickSortByRole();
        return this;
    }

    @Step("Verify ascending role order")
    public PlayGroundTablesSteps verifyRolesSortedAscending() {
        List<String> roles = allTexts(playGroundTablesPage::roleCells);
        assertEquals(roles.stream().sorted().toList(), roles);
        return this;
    }

    @Step("Go to the next table page")
    public PlayGroundTablesSteps goToNextPage() {
        playGroundTablesPage.clickNextPage();
        return this;
    }

    @Step("Go to the previous table page")
    public PlayGroundTablesSteps goToPreviousPage() {
        playGroundTablesPage.clickPreviousPage();
        return this;
    }

    @Step("Verify current table page: {page}")
    public PlayGroundTablesSteps verifyCurrentPage(String page) {
        playGroundTablesPage.verifyCurrentPage(page);
        return this;
    }

    @Step("Remember the current page rows")
    public PlayGroundTablesSteps rememberCurrentPageRows() {
        rememberedPageRows = nonEmptyTexts(playGroundTablesPage.tableRows());
        return this;
    }

    @Step("Verify that the current page rows changed")
    public PlayGroundTablesSteps verifyCurrentPageRowsChanged() {
        assertNotEquals(
                rememberedRows(),
                nonEmptyTexts(playGroundTablesPage.tableRows()),
                "Pagination must display different rows"
        );
        return this;
    }

    @Step("Verify that the remembered page rows were restored")
    public PlayGroundTablesSteps verifyRememberedPageRowsRestored() {
        assertEquals(
                rememberedRows(),
                nonEmptyTexts(playGroundTablesPage.tableRows()),
                "Returning to the first page must restore its rows"
        );
        return this;
    }

    private List<String> rememberedRows() {
        if (rememberedPageRows == null) {
            throw new IllegalStateException("Page rows must be remembered before comparison");
        }
        return rememberedPageRows;
    }

    private List<Integer> allIds() {
        return allTexts(playGroundTablesPage::idCells)
                .stream()
                .map(Integer::parseInt)
                .toList();
    }

    private List<String> allTexts(java.util.function.Supplier<ElementsCollection> cells) {
        List<String> values = new ArrayList<>();
        while (true) {
            values.addAll(nonEmptyTexts(cells.get()));
            if (!playGroundTablesPage.nextPageIsEnabled()) {
                break;
            }

            String[] pageState = playGroundTablesPage.currentPageState().split(" / ");
            int nextPage = Integer.parseInt(pageState[0]) + 1;
            String expectedState = nextPage + " / " + pageState[1];
            playGroundTablesPage.clickNextPage().verifyCurrentPage(expectedState);
        }

        if (values.size() < 2) {
            throw new AssertionError("Sorting requires at least two displayed values");
        }
        return values;
    }

    private List<String> nonEmptyTexts(ElementsCollection elements) {
        return elements.shouldHave(sizeGreaterThan(0)).texts();
    }
}
