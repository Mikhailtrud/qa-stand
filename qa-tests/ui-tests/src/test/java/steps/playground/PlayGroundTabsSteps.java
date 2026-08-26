package steps.playground;

import com.codeborne.selenide.Condition;
import pages.playground.PlayGroundTabsPage;
import steps.BaseSteps;

public class PlayGroundTabsSteps extends BaseSteps {

    private final PlayGroundTabsPage playGroundTabsPage =
            new PlayGroundTabsPage();

    public PlayGroundTabsSteps openPlayGroundPage() {
        step("Открыть страницу PlayGround");
        playGroundPage.open();
        return this;
    }

    public PlayGroundTabsSteps verifyPlaygroundTabsVisible() {
        step("Проверить видимость блока с вкладками");
        playGroundPage.tabsBlock().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundTabsSteps selectTab(int number) {
        step("Выбрать Tab " + number);
        playGroundTabsPage.clickTab(number);
        return this;
    }

    public PlayGroundTabsSteps verifyTabContent(String expectedText) {
        step("Проверить содержимое таба: " + expectedText);
        playGroundTabsPage.verifyTabContent(expectedText);
        return this;
    }

}
