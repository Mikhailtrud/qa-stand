package steps.playground;

import com.codeborne.selenide.Condition;
import steps.BaseSteps;

public class PlayGroundTablesSteps extends BaseSteps {

    public PlayGroundTablesSteps openPlayGroundPage() {
        step("Открыть страницу PlayGround");
        playGroundPage.open();
        return this;
    }

    public PlayGroundTablesSteps verifyPlaygroundTablesVisible() {
        step("Проверить видимость блока с таблицей");
        playGroundPage.tablesBlock().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundTablesSteps enterSearchValue(String value) {
        step("Ввести значение в поиск: " + value);
        playGroundTablesPage.enterSearchValue(value);
        return this;
    }

    public PlayGroundTablesSteps verifyTableContainsText(String text) {
        step("Проверить наличие текста в таблице: " + text);
        playGroundTablesPage.verifyTableContainsText(text);
        return this;
    }

    public PlayGroundTablesSteps selectRoleFilter(String role) {
        step("Выбрать фильтр по роли: " + role);
        playGroundTablesPage.selectRoleFilter(role);
        return this;
    }

    public PlayGroundTablesSteps verifyAllRowsContainRole(String role) {
        step("Проверить, что все строки содержат роль: " + role);
        playGroundTablesPage.verifyAllRowsContainRole(role);
        return this;
    }

    public PlayGroundTablesSteps sortById() {
        step("Отсортировать таблицу по ID");
        playGroundTablesPage.clickSortById();
        return this;
    }

    public PlayGroundTablesSteps verifyIdsSortedAscending() {
        step("Проверить сортировку ID по возрастанию");
        playGroundTablesPage.verifyIdsSortedAscending();
        return this;
    }

    public PlayGroundTablesSteps verifyIdsSortedDescending() {
        step("Проверить сортировку ID по убыванию");
        playGroundTablesPage.verifyIdsSortedDescending();
        return this;
    }

    public PlayGroundTablesSteps sortByName() {
        step("Отсортировать таблицу по имени");
        playGroundTablesPage.clickSortByName();
        return this;
    }

    public PlayGroundTablesSteps verifyNamesSortedAscending() {
        step("Проверить сортировку имён по возрастанию");
        playGroundTablesPage.verifyNamesSortedAscending();
        return this;
    }

    public PlayGroundTablesSteps goToNextPage() {
        step("Перейти на следующую страницу");
        playGroundTablesPage.clickNextPage();
        return this;
    }

    public PlayGroundTablesSteps goToPreviousPage() {
        step("Перейти на предыдущую страницу");
        playGroundTablesPage.clickPreviousPage();
        return this;
    }

    public PlayGroundTablesSteps verifyCurrentPage(String page) {
        step("Проверить текущую страницу: " + page);
        playGroundTablesPage.verifyCurrentPage(page);
        return this;
    }
}
