package auth;

import api.ApiAuthClient;
import config.AppiumConfig;
import driver.DriverManager;
import java.util.Map;

public final class IntentAuthProvider {
    private static final String TEST_AUTH_ACTIVITY = ".TestAuthActivity";
    private static final String TOKEN_EXTRA = "com.qastand.android.extra.AUTH_TOKEN";

    private final ApiAuthClient authClient = new ApiAuthClient();

    public void authorizeAsAdmin() {
        String token = authClient.loginAsAdmin();
        DriverManager.getDriver().executeScript(
                "mobile: startActivity",
                Map.of(
                        "component", AppiumConfig.appPackage() + "/" + TEST_AUTH_ACTIVITY,
                        "extras", new String[][]{{"s", TOKEN_EXTRA, token}},
                        "wait", true,
                        "stop", true
                )
        );
    }

}
