package tests.playGroundTests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Epic("QA Stand")
@Feature("Playground: Tables")
public class TablesTests extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTablesVisible();
    }

    @Test
    @Story("Search")
    void searchByNameTest() {
        playGroundTablesSteps
                .enterSearchValue("John")
                .verifySearchResultsVisible()
                .verifySearchResults("John");
    }

    @Test
    @Story("Role filter")
    void filterByRoleTest() {
        playGroundTablesSteps
                .selectRoleFilter("ADMIN")
                .verifyDisplayedRowsCount(2)
                .verifyAllRowsContainRole("ADMIN");
    }

    @Test
    @Story("ID sorting")
    void sortByIdDescendingTest() {
        playGroundTablesSteps
                .sortById()
                .verifyIdsSortedDescending();
    }

    @Test
    @Story("ID sorting")
    void sortByIdAscendingTest() {
        playGroundTablesSteps
                .sortById()
                .sortById()
                .verifyIdsSortedAscending();
    }

    @Test
    @Story("Name sorting")
    void sortByNameTest() {
        playGroundTablesSteps
                .sortByName()
                .verifyNamesSortedAscending();
    }

    @Test
    @Story("Pagination")
    void paginationNextPageTest() {
        playGroundTablesSteps
                .rememberCurrentPageRows()
                .goToNextPage()
                .verifyCurrentPage("2 / 3")
                .verifyCurrentPageRowsChanged();
    }

    @Test
    @Story("Pagination")
    void paginationPreviousPageTest() {
        playGroundTablesSteps
                .rememberCurrentPageRows()
                .goToNextPage()
                .verifyCurrentPage("2 / 3")
                .verifyCurrentPageRowsChanged()
                .goToPreviousPage()
                .verifyCurrentPage("1 / 3")
                .verifyRememberedPageRowsRestored();
    }

    @Test
    @Story("Role sorting")
    void sortByRoleAscendingTest() {
        playGroundTablesSteps
                .sortByRole()
                .verifyRolesSortedAscending();
    }


}
