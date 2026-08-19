package config;

import api.ApiHelper;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

public class AuthenticatedTest extends BaseTest {

    protected static final String AUTH_TOKEN_KEY = "authToken";

    protected final ApiHelper apiHelper = new ApiHelper();
    protected String authToken;

    @BeforeEach
    void authenticate() {
        authToken = apiHelper.login();

        open("/");
        executeJavaScript(
                "localStorage.setItem(arguments[0], arguments[1])",
                AUTH_TOKEN_KEY,
                authToken
        );
        refresh();
    }
}
