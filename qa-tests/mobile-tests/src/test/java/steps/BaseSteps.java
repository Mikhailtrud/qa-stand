package steps;

import pages.*;

public abstract class BaseSteps {
    protected final CreateUserPage createUserPage = new CreateUserPage();
    protected final EditUserPage editUserPage = new EditUserPage();
    protected final LoginPage loginPage = new LoginPage();
    protected final UsersPage usersPage = new UsersPage();
}
