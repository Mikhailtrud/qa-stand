package steps;

import io.qameta.allure.Allure;
import pages.LoginPage;
import pages.UsersPage;

public class BaseSteps {

    protected final LoginPage loginPage = new LoginPage();
    protected final UsersPage usersPage = new UsersPage();

    protected void step(String message) {
        Allure.step(message);
    }

}