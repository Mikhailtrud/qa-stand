package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import pages.UsersPage;

public final class UsersSteps {
    private final UsersPage page;

    public UsersSteps(AndroidDriver driver) {
        this.page = new UsersPage(driver);
    }

    @Step("Refresh users")
    public void refreshUsers() {
        page.refresh();
    }

    @Step("Verify the users list is displayed")
    public void verifyUsersListDisplayed() {
        assertThat(page.isUsersListDisplayed()).isTrue();
    }
}
