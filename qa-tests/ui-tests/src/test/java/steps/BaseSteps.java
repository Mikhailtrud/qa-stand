package steps;

import pages.*;
import pages.playground.*;

public class BaseSteps {

    protected final LoginPage loginPage = new LoginPage();
    protected final UsersPage usersPage = new UsersPage();
    protected final PlayGroundDynamicMousePage playGroundDynamicMousePage = new PlayGroundDynamicMousePage();
    protected final PlayGroundFormsPage playGroundFormsPage = new PlayGroundFormsPage();
    protected final PlayGroundJavaScriptPage playGroundJavaScriptPage = new PlayGroundJavaScriptPage();
    protected final PlayGroundMousePage playGroundMousePage = new PlayGroundMousePage();
    protected final PlayGroundPage playGroundPage = new PlayGroundPage();
    protected final PlayGroundTablesPage playGroundTablesPage = new PlayGroundTablesPage();

}
