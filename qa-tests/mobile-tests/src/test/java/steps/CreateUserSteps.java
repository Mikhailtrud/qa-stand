package steps;

import pages.CreateUserPage;

public final class CreateUserSteps extends BaseSteps {
    private final CreateUserPage page = new CreateUserPage();

    public void fillForm(String name, String email, String password, String role) {
        page.enterName(name).enterEmail(email).enterPassword(password).enterRole(role);
    }
}
