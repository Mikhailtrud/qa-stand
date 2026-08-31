package steps;

import pages.EditUserPage;

public final class EditUserSteps extends BaseSteps {
    private final EditUserPage page = new EditUserPage();

    public void replaceForm(String name, String email, String role) {
        page.replaceName(name).replaceEmail(email).replaceRole(role);
    }
}
