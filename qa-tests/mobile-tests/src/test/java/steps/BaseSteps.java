package steps;

import framework.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import pages.*;

public abstract class BaseSteps {
    protected AndroidDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected final CreateUserPage createUserPage = new CreateUserPage();
    protected final EditUserPage editUserPage = new EditUserPage();
    protected final LoginPage loginPage = new LoginPage();
    protected final UsersPage usersPage = new UsersPage();
    protected final PlaygroundPage playgroundPage = new PlaygroundPage();
}
