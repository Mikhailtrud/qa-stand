package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

import java.nio.file.Path;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PlayGroundTablesPage extends BasePage{

     //Tables
    private final SelenideElement tableSearchField =
            $("[data-testid='table-search']");

    private final SelenideElement tableFilter =
            $("[data-testid='role-filter']");

    private final SelenideElement tableFilterOptions =
            $("[data-testid='role-filter'] option");

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

    private final SelenideElement tableRaw1Cells =
            $("[data-testid='table-row-1'] td");

    private final SelenideElement tableRaws =
            $("[data-testid='dynamic-table'] tbody tr");

    private final SelenideElement tableRawsCells =
            $("[data-testid='dynamic-table'] tbody tr td");


}
