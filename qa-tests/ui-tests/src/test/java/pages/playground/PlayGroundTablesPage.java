package pages.playground;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayGroundTablesPage extends BasePage {

    //Elements
    // Tables
    private final SelenideElement tableSearchField =
            $("[data-testid='table-search']");

    private final SelenideElement tableFilter =
            $("[data-testid='role-filter']");

    private final SelenideElement tablePagePrevious =
            $("[data-testid='page-1']");

    private final SelenideElement tablePageNext =
            $("[data-testid='page-2']");

    private final SelenideElement tablesCurrentPage =
            $("[data-testid='current-page']");

    private final SelenideElement tableSortByID =
            $("[data-testid='sort-id']");

    private final SelenideElement tableSortByName =
            $("[data-testid='sort-name']");

    private final SelenideElement tableSortByRole =
            $("[data-testid='sort-role']");

    //Functions
    public ElementsCollection tableRows() {
        return $$("[data-testid='dynamic-table'] tbody tr");
    }

    public ElementsCollection idCells() {
        return $$("[data-testid='dynamic-table'] tbody tr td:nth-child(1)");
    }

    public ElementsCollection nameCells() {
        return $$("[data-testid='dynamic-table'] tbody tr td:nth-child(2)");
    }

    public ElementsCollection roleCells() {
        return $$("[data-testid='dynamic-table'] tbody tr td:nth-child(3)");
    }


    public PlayGroundTablesPage enterSearchValue(String value) {
        tableSearchField.setValue(value);
        return this;
    }

    public PlayGroundTablesPage verifyTableContainsText(String expectedText) {
        tableRows()
                .findBy(text(expectedText))
                .shouldBe(visible);

        return this;
    }

    public PlayGroundTablesPage selectRoleFilter(String role) {
        tableFilter.selectOption(role);
        return this;
    }

    public PlayGroundTablesPage verifyAllRowsContainRole(String role) {
        roleCells().forEach(
                cell -> cell.shouldHave(text(role))
        );

        return this;
    }

    public PlayGroundTablesPage clickSortById() {
        tableSortByID.click();
        return this;
    }

    public PlayGroundTablesPage verifyIdsSortedAscending() {
        List<Integer> actualIds = idCells()
                .texts()
                .stream()
                .map(Integer::parseInt)
                .toList();

        List<Integer> expectedIds = actualIds
                .stream()
                .sorted()
                .toList();

        assertEquals(expectedIds, actualIds);

        return this;
    }

    public PlayGroundTablesPage verifyIdsSortedDescending() {
        List<Integer> actualIds = idCells()
                .texts()
                .stream()
                .map(Integer::parseInt)
                .toList();

        List<Integer> expectedIds = actualIds
                .stream()
                .sorted((a, b) -> b.compareTo(a))
                .toList();

        assertEquals(expectedIds, actualIds);

        return this;
    }

    public PlayGroundTablesPage clickSortByName() {
        tableSortByName.click();
        return this;
    }

    public PlayGroundTablesPage verifyNamesSortedAscending() {
        List<String> actualNames = nameCells().texts();

        List<String> expectedNames = actualNames
                .stream()
                .sorted()
                .toList();

        assertEquals(expectedNames, actualNames);

        return this;
    }

    public PlayGroundTablesPage clickSortByRole() {
        tableSortByRole.click();
        return this;
    }

    public PlayGroundTablesPage verifyRolesSortedAscending() {
        List<String> actualRoles = roleCells().texts();

        List<String> expectedRoles = actualRoles
                .stream()
                .sorted()
                .toList();

        assertEquals(expectedRoles, actualRoles);

        return this;
    }

    public PlayGroundTablesPage clickNextPage() {
        tablePageNext.click();
        return this;
    }

    public PlayGroundTablesPage clickPreviousPage() {
        tablePagePrevious.click();
        return this;
    }

    public PlayGroundTablesPage verifyCurrentPage(String page) {
        tablesCurrentPage.shouldHave(text(page));
        return this;
    }
}