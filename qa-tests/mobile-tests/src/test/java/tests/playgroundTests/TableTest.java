package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.BaseTest;

@Epic("QA Stand Mobile")
@Feature("QA Playground")
@Story("Tables")
public class TableTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        usersSteps.openPlayground();
        playgroundTableSteps
                .verifyPlaygroundDisplayed()
                .scrollToTable();
    }

    @Test
    @Description("Searching by name displays the matching table row")
    void searchFiltersRowsByName() {
        playgroundTableSteps
                .focusTableSearch()
                .enterTableSearch("John")
                .verifyNameAfterSearch("John")
                .verifyTableRow(1, "John", "ADMIN");
    }

    @ParameterizedTest
    @Description("Filtering by role displays only rows with matching roles")
    @CsvSource({
            "USER, USER, USER",
            "ADMIN, ADMIN, ADMIN",
            "ALL, ADMIN, USER"
    })
    void roleFilterLimitsDisplayedRows(
            String filter,
            String firstRole,
            String secondRole
    ) {
        playgroundTableSteps
                .openRoleFilter()
                .selectRoleFilter(filter)
                .verifyColumnValues("Role", firstRole, secondRole);
    }

    @Test
    @Description("Table pagination displays the expected rows on each page")
    void paginationNavigatesBetweenPages() {
        playgroundTableSteps
                .openNextTablePage()
                .verifyTableRow(3, "Mike", "USER")
                .verifyTableRow(4, "Sara", "ADMIN")
                .openPreviousTablePage()
                .verifyTableRow(1, "John", "ADMIN")
                .verifyTableRow(2, "Kate", "USER");
    }

    @Test
    @Description("Sorting by ID orders the visible ID values")
    void idColumnCanBeSorted() {
        playgroundTableSteps
                .sortById()
                .verifyColumnValues("ID", "5", "4");
    }

    @Test
    @Description("Sorting by Name orders the visible name values")
    void nameColumnCanBeSorted() {
        playgroundTableSteps
                .sortByName()
                .verifyColumnValues("Name", "Tom", "Sara");
    }

    @Test
    @Description("Sorting by Role orders the visible role values")
    void roleColumnCanBeSorted() {
        playgroundTableSteps
                .sortByRole()
                .verifyColumnValues("Role", "USER", "USER");
    }


}
