package tests.authTests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.junit.jupiter.api.Assertions.assertNull;

@Epic("QA Stand")
@Feature("Authentication")
@Story("Logout")
public class LogoutTest extends AuthenticatedTest {

    @Test
    void logoutClearsAuthentication() {
        loginSteps
                .logout()
                .verifyLoginLabelVisible();

        String storedToken = executeJavaScript(
                "return localStorage.getItem(arguments[0])",
                AUTH_TOKEN_KEY
        );
        assertNull(storedToken);
    }
}
