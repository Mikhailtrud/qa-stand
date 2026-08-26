package steps;

import io.appium.java_client.android.AndroidDriver;
import pages.EditUserPage;

public final class EditUserSteps {
    private final EditUserPage page;

    public EditUserSteps(AndroidDriver driver) {
        this.page = new EditUserPage(driver);
    }

    public void replaceForm(String name, String email, String role) {
        page.replaceName(name).replaceEmail(email).replaceRole(role);
    }
}
