package steps;

import io.qameta.allure.Step;
import pages.PlaygroundTablePage;

import static org.assertj.core.api.Assertions.assertThat;

public final class PlaygroundTableSteps extends BaseSteps {
    private final PlaygroundTablePage playgroundTablePage = new PlaygroundTablePage();

    @Step("Verify QA Playground is displayed")
    public PlaygroundTableSteps verifyPlaygroundDisplayed() {
        assertThat(playgroundTablePage.isDisplayed()).isTrue();
        return this;
    }

    @Step("Scroll to the table")
    public PlaygroundTableSteps scrollToTable() {
        playgroundTablePage.scrollToTable();
        return this;
    }

    @Step("Focus the table search field")
    public PlaygroundTableSteps focusTableSearch() {
        playgroundTablePage.focusTableSearch();
        return this;
    }

    @Step("Enter table search text: {text}")
    public PlaygroundTableSteps enterTableSearch(String text) {
        playgroundTablePage.enterTableSearch(text);
        return this;
    }

    @Step("Verify name {name}")
    public PlaygroundTableSteps verifyNameAfterSearch(String name) {
        assertThat(playgroundTablePage.getTableColumnValues("Name")).containsExactly(name);
        return this;
    }

    @Step("Verify table row: {id}, {name}, {role}")
    public PlaygroundTableSteps verifyTableRow(int id, String name, String role) {
        assertThat(playgroundTablePage.getTableRowValues(id))
                .containsExactly(String.valueOf(id), name, role);
        return this;
    }

    @Step("Open role filter")
    public PlaygroundTableSteps openRoleFilter() {
        playgroundTablePage.openRoleFilter();
        return this;
    }

    @Step("Select role filter: {role}")
    public PlaygroundTableSteps selectRoleFilter(String role) {
        playgroundTablePage.selectRoleFilter(role);
        return this;
    }

    @Step("Verify column {columnName} values")
    public PlaygroundTableSteps verifyColumnValues(String columnName, String... expectedValues) {
        assertThat(playgroundTablePage.getTableColumnValues(columnName)).containsExactly(expectedValues);
        return this;
    }

    @Step("Open next page")
    public PlaygroundTableSteps openNextTablePage() {
        playgroundTablePage.openNextTablePage();
        return this;
    }

    @Step("Open previous page")
    public PlaygroundTableSteps openPreviousTablePage() {
        playgroundTablePage.openPreviousTablePage();
        return this;
    }

    @Step("Sort by ID")
    public PlaygroundTableSteps sortById() {
        playgroundTablePage.sortById();
        return this;
    }

    @Step("Sort by Name")
    public PlaygroundTableSteps sortByName() {
        playgroundTablePage.sortByName();
        return this;
    }

    @Step("Sort by Role")
    public PlaygroundTableSteps sortByRole() {
        playgroundTablePage.sortByRole();
        return this;
    }
}
