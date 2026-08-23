package steps;

import io.qameta.allure.Allure;
import pages.LoginPage;
import pages.PlayGroundFormsPage;
import pages.PlayGroundJavaScriptPage;
import pages.UsersPage;

public class BaseSteps {

    protected final LoginPage loginPage = new LoginPage();
    protected final UsersPage usersPage = new UsersPage();
    protected final PlayGroundFormsPage playGroundFormsPage = new PlayGroundFormsPage();
    protected final PlayGroundJavaScriptPage playGroundJavaScriptPage = new PlayGroundJavaScriptPage();

    protected void step(String message) {
        Allure.step(message);
    }

}