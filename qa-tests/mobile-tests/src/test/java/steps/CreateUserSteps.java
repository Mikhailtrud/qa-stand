package steps;

import io.appium.java_client.android.AndroidDriver;
import pages.CreateUserPage;

public final class CreateUserSteps {
    private final CreateUserPage page;

    public CreateUserSteps(AndroidDriver driver) {
        this.page = new CreateUserPage(driver);
    }

    public void fillForm(String name, String email, String password, String role) {
        page.enterName(name).enterEmail(email).enterPassword(password).enterRole(role);
    }
}
