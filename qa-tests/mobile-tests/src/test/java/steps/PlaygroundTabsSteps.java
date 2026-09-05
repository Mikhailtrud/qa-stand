package steps;

import io.qameta.allure.Step;
import pages.PlaygroundTabsPage;

public final class PlaygroundTabsSteps extends BaseSteps {
    private final PlaygroundTabsPage playgroundTabsPage = new PlaygroundTabsPage();

    @Step("Verify QA Playground is displayed")
    public PlaygroundTabsSteps verifyPlaygroundDisplayed() {
        org.assertj.core.api.Assertions.assertThat(playgroundTabsPage.isDisplayed()).isTrue();
        return this;
    }

    @Step("Select tab {tab}")
    public PlaygroundTabsSteps selectTab(int tab) {
        playgroundTabsPage.selectTab(tab);
        return this;
    }

    @Step("Verify tab content: {expectedText}")
    public PlaygroundTabsSteps verifyTabContent(String expectedText) {
        playgroundTabsPage.verifyTabContent(expectedText);
        return this;
    }
}
