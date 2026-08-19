package tests.authTests;

import config.AuthenticatedTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LogoutTest extends AuthenticatedTest {

    @Test
    void logoutClearsAuthentication() {
        loginSteps.logout()
                .verifyLoginLabelVisible();

        String storedToken = executeJavaScript(
                "return localStorage.getItem(arguments[0])",
                AUTH_TOKEN_KEY
        );
        assertNull(storedToken);
    }
}
