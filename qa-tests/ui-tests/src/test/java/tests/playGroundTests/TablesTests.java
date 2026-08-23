package tests.playGroundTests;

import config.AuthenticatedTest;
import org.junit.jupiter.api.Test;

public class TablesTests extends AuthenticatedTest {

    @Test
    void searchByNameTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTabsVisible()
                .enterSearchValue("John")
                .verifyTableContainsText("John");
    }

    @Test
    void filterByRoleTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTabsVisible()
                .selectRoleFilter("ADMIN")
                .verifyAllRowsContainRole("ADMIN");
    }

    @Test
    void sortByIdDescendingTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTabsVisible()
                .sortById()
                .verifyIdsSortedDescending();
    }

    @Test
    void sortByIdAscendingTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTabsVisible()
                .sortById()
                .sortById()
                .verifyIdsSortedAscending();
    }

    @Test
    void sortByNameTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTabsVisible()
                .sortByName()
                .verifyNamesSortedAscending();
    }

    @Test
    void paginationNextPageTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTabsVisible()
                .goToNextPage()
                .verifyCurrentPage("2");
    }

    @Test
    void paginationPreviousPageTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTabsVisible()
                .goToNextPage()
                .verifyCurrentPage("2")
                .goToPreviousPage()
                .verifyCurrentPage("1");
    }


}
