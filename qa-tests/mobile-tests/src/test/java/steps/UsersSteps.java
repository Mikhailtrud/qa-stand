package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import pages.UsersPage;

public final class UsersSteps extends BaseSteps {
    private final UsersPage page = new UsersPage();

    @Step("Verify the Users screen is displayed")
    public void verifyUsersScreenDisplayed() {
        assertThat(page.isDisplayed())
                .as("Users screen should be visible after login")
                .isTrue();
    }

    @Step("Refresh users")
    public void refreshUsers() {
        page.refresh();
    }

    @Step("Verify the users list is displayed")
    public void verifyUsersListDisplayed() {
        assertThat(page.isUsersListDisplayed()).isTrue();
    }

    @Step("Log out")
    public void logout() {
        page.logout();
    }
}
