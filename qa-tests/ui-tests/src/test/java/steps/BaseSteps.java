package steps;

import io.qameta.allure.Allure;
import pages.UsersPage;

public class BaseSteps {

    protected final UsersPage usersPage = new UsersPage();

    protected void step(String message) {
        Allure.step(message);
    }

}