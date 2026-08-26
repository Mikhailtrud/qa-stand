package steps;

import static org.assertj.core.api.Assertions.assertThat;

import config.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import pages.LoginPage;
import pages.UsersPage;

public final class LoginSteps {
    private final LoginPage loginPage;
    private final UsersPage usersPage;

    public LoginSteps(AndroidDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.usersPage = new UsersPage(driver);
    }

    @Step("Log in as the configured administrator")
    public void loginAsAdmin() {
        loginPage.enterEmail(TestConfig.adminEmail())
                .enterPassword(TestConfig.adminPassword())
                .tapLogin();
    }

    @Step("Verify the Users screen is displayed")
    public void verifyUsersScreenDisplayed() {
        assertThat(usersPage.isDisplayed())
                .as("Users screen should be visible after login")
                .isTrue();
    }
}
