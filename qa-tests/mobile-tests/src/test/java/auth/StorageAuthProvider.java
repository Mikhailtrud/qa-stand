package auth;

import api.ApiAuthClient;
import config.AppiumConfig;
import driver.DriverManager;

public final class StorageAuthProvider {
    private final ApiAuthClient authClient = new ApiAuthClient();
    private final AdbAppStorage appStorage = new AdbAppStorage();

    public void authorizeAsAdmin() {
        String token = authClient.loginAsAdmin();
        appStorage.writeToken(token);
        DriverManager.getDriver().activateApp(AppiumConfig.appPackage());
    }
}
