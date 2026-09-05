package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.BaseTest;

public class TableTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        playgroundSteps
                .openPlayground()
                .verifyPlaygroundDisplayed()
                .scrollToTable();
    }

    @Test
    @Description("")
    void searchByNameTest() {
        playgroundSteps
                .tableSearchClick()
                .tableSearchFillText("John")
                .verifyNameAfterSearch("John")
                .verifyTableRow(1, "John", "ADMIN");
    }

    @ParameterizedTest
    @CsvSource({
            "USER, USER, USER",
            "ADMIN, ADMIN, ADMIN",
            "ALL, ADMIN, USER"
    })
    void filterByRoleTest(
            String filter,
            String firstRole,
            String secondRole
    ) {
        playgroundSteps
                .openFilterClick()
                .roleFilterClick(filter)
                .verifyColumnValues("Role", firstRole, secondRole);
    }

    @Test
    @Description("")
    void paginationNextPageTest() {
        playgroundSteps
                .tableNextButtonClick()
                .verifyTableRow(3, "Mike", "USER")
                .verifyTableRow(4, "Sara", "ADMIN")
                .tablePreviousButtonClick()
                .verifyTableRow(1, "John", "ADMIN")
                .verifyTableRow(2, "Kate", "USER");
    }

    @Test
    @Description("")
    void sortByIdTest() {
        playgroundSteps
                .sortByIdClick()
                .verifyTableRow(5, "Tom", "USER")
                .verifyTableRow(4, "Sara", "ADMIN");
    }

    @Test
    @Description("")
    void sortByNameTest() {
        playgroundSteps
                .sortByNameClick()
                .verifyTableRow(5, "Tom", "USER")
                .verifyTableRow(4, "Sara", "ADMIN");
    }

    @Test
    @Description("")
    void sortByRoleTest() {
        playgroundSteps
                .sortByRoleClick()
                .verifyTableRow(2, "Kate", "USER")
                .verifyTableRow(3, "Mike", "USER");
    }


}
