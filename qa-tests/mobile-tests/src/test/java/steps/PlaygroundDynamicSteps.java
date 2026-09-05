package steps;

import io.qameta.allure.Step;
import pages.PlaygroundDynamicPage;

import static org.assertj.core.api.Assertions.assertThat;

public final class PlaygroundDynamicSteps extends BaseSteps {
    private final PlaygroundDynamicPage playgroundDynamicPage = new PlaygroundDynamicPage();

    @Step("Verify QA Playground is displayed")
    public PlaygroundDynamicSteps verifyPlaygroundDisplayed() {
        assertThat(playgroundDynamicPage.isDisplayed()).isTrue();
        return this;
    }

    @Step("Scroll to the bottom of QA Playground")
    public PlaygroundDynamicSteps scrollToBottom() {
        playgroundDynamicPage.scrollToBottom();
        return this;
    }

    @Step("Start the loader")
    public PlaygroundDynamicSteps startLoader() {
        playgroundDynamicPage.startLoader();
        return this;
    }

    @Step("Verify loader is displayed")
    public PlaygroundDynamicSteps verifyLoaderDisplayed() {
        assertThat(playgroundDynamicPage.isLoaderDisplayed()).isTrue();
        return this;
    }

    @Step("Show delayed button")
    public PlaygroundDynamicSteps showDelayedButton() {
        playgroundDynamicPage.showDelayedButton();
        return this;
    }

    @Step("Verify delayed button is displayed")
    public PlaygroundDynamicSteps verifyDelayedButtonDisplayed() {
        assertThat(playgroundDynamicPage.isDelayedButtonDisplayed()).isTrue();
        return this;
    }

    @Step("Verify hidden element text: {expectedText}")
    public PlaygroundDynamicSteps verifyHiddenElementText(String expectedText) {
        assertThat(playgroundDynamicPage.getHiddenElementText()).isEqualTo(expectedText);
        return this;
    }
}
