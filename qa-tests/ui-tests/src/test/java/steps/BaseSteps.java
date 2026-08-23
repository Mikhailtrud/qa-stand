package steps;

import io.qameta.allure.Allure;
import pages.*;

public class BaseSteps {

    protected final LoginPage loginPage = new LoginPage();
    protected final UsersPage usersPage = new UsersPage();
    protected final PlayGroundFormsPage playGroundFormsPage = new PlayGroundFormsPage();
    protected final PlayGroundJavaScriptPage playGroundJavaScriptPage = new PlayGroundJavaScriptPage();
    protected final PlayGroundTabsPage playGroundTabsPage = new PlayGroundTabsPage();
    protected final PlayGroundTablesPage playGroundTablesPage = new PlayGroundTablesPage();
    protected final PlayGroundDynamicMousePage playGroundDynamicMousePage = new PlayGroundDynamicMousePage();

    protected void step(String message) {
        Allure.step(message);
    }

}