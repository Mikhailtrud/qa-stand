package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import pages.PlaygroundPage;
import pages.UsersPage;

public final class PlaygroundSteps {
    private final UsersPage usersPage;
    private final PlaygroundPage playgroundPage;

    public PlaygroundSteps(AndroidDriver driver) {
        this.usersPage = new UsersPage(driver);
        this.playgroundPage = new PlaygroundPage(driver);
    }

    @Step("Open QA Playground")
    public void openPlayground() {
        usersPage.openPlayground();
    }

    @Step("Verify QA Playground is displayed")
    public void verifyPlaygroundDisplayed() {
        assertThat(playgroundPage.isDisplayed()).isTrue();
    }
}
