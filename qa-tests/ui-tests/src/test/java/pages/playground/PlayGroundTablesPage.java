package pages.playground;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    public PlayGroundTablesPage selectRoleFilter(String role) {
        tableFilter.selectOption(role);
        return this;
    }

    public PlayGroundTablesPage clickSortById() {
        tableSortByID.click();
        return this;
    }

    public PlayGroundTablesPage clickSortByName() {
        tableSortByName.click();
        return this;
    }

    public PlayGroundTablesPage clickSortByRole() {
        tableSortByRole.click();
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

    public PlayGroundTablesPage verifyCurrentPage(String pageState) {
        tablesCurrentPage.shouldHave(exactText(pageState));
        return this;
    }

    public String currentPageState() {
        return tablesCurrentPage.text();
    }

    public boolean nextPageIsEnabled() {
        return tablePageNext.isEnabled();
    }
}
