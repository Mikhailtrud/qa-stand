package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import pages.PlaygroundPage;
import pages.UsersPage;

public final class PlaygroundSteps extends BaseSteps {
    private final UsersPage usersPage = new UsersPage();
    private final PlaygroundPage playgroundPage = new PlaygroundPage();

    @Step("Open QA Playground")
    public void openPlayground() {
        usersPage.openPlayground();
    }

    @Step("Verify QA Playground is displayed")
    public void verifyPlaygroundDisplayed() {
        assertThat(playgroundPage.isDisplayed()).isTrue();
    }
}
