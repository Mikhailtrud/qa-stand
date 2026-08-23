package tests.playGroundTests;

import config.AuthenticatedTest;
import org.junit.jupiter.api.Test;

public class TablesTests extends AuthenticatedTest {

    @Test
    void searchByNameTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTablesVisible()
                .enterSearchValue("John")
                .verifyTableContainsText("John");
    }

    @Test
    void filterByRoleTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTablesVisible()
                .selectRoleFilter("ADMIN")
                .verifyAllRowsContainRole("ADMIN");
    }

    @Test
    void sortByIdDescendingTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTablesVisible()
                .sortById()
                .verifyIdsSortedDescending();
    }

    @Test
    void sortByIdAscendingTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTablesVisible()
                .sortById()
                .sortById()
                .verifyIdsSortedAscending();
    }

    @Test
    void sortByNameTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTablesVisible()
                .sortByName()
                .verifyNamesSortedAscending();
    }

    @Test
    void paginationNextPageTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTablesVisible()
                .goToNextPage()
                .verifyCurrentPage("2");
    }

    @Test
    void paginationPreviousPageTest() {
        playGroundTablesSteps
                .openPlayGroundPage()
                .verifyPlaygroundTablesVisible()
                .goToNextPage()
                .verifyCurrentPage("2")
                .goToPreviousPage()
                .verifyCurrentPage("1");
    }


}
