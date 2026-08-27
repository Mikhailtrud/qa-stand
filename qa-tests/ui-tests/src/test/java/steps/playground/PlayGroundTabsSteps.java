package steps.playground;

import com.codeborne.selenide.Condition;
import pages.playground.PlayGroundTabsPage;
import steps.BaseSteps;
import io.qameta.allure.Step;

public class PlayGroundTabsSteps extends BaseSteps {

    private final PlayGroundTabsPage playGroundTabsPage =
            new PlayGroundTabsPage();

    @Step("Open the Playground page")
    public PlayGroundTabsSteps openPlayGroundPage() {
        playGroundPage.open();
        return this;
    }

    @Step("Verify that the Tabs section is visible")
    public PlayGroundTabsSteps verifyPlaygroundTabsVisible() {
        playGroundPage.tabsBlock().shouldBe(Condition.visible);
        return this;
    }

    @Step("Select tab {number}")
    public PlayGroundTabsSteps selectTab(int number) {
        playGroundTabsPage.clickTab(number);
        return this;
    }

    @Step("Verify tab content: {expectedText}")
    public PlayGroundTabsSteps verifyTabContent(String expectedText) {
        playGroundTabsPage.verifyTabContent(expectedText);
        return this;
    }

}
