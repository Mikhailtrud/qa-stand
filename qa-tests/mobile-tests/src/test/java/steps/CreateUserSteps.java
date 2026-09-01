package steps;

import data.testData.UserData;
import io.qameta.allure.Step;
import pages.CreateUserPage;

import static org.assertj.core.api.Assertions.assertThat;

public final class CreateUserSteps extends BaseSteps {
    private final CreateUserPage createUserPage = new CreateUserPage();

    @Step("Fill the create user form")
    public CreateUserSteps fillForm(String name, String email, String password, String role) {
        createUserPage.enterName(name).enterEmail(email).enterPassword(password).enterRole(role);
        return this;
    }

    public CreateUserSteps fillForm(UserData user) {
        return fillForm(user.name(), user.email(), user.password(), user.role());
    }

    @Step("Error message should be visible")
    public CreateUserSteps verifyErrorMessageShouldBeVisible() {
        assertThat(createUserPage.isErrorMessageDisplayed()).isTrue();
        return this;
    }

    @Step("Save button should be disabled")
    public CreateUserSteps verifySaveButtonEnabled() {
        assertThat(createUserPage.isSaveButtonEnabled()).isTrue();
        return this;
    }

    @Step("Save the new user")
    public CreateUserSteps save() {
        createUserPage.save();
        return this;
    }

    @Step("Save button should not be clickable")
    public CreateUserSteps verifySaveButtonNotClickable() {
        assertThat(createUserPage.isSaveButtonClickable()).isFalse();
        return this;
    }

    @Step("Save button should be clickable")
    public CreateUserSteps verifySaveButtonClickable() {
        assertThat(createUserPage.isSaveButtonClickable()).isTrue();
        return this;
    }

    @Step("Validation error should be displayed: {expectedError}")
    public CreateUserSteps verifyValidationError(String expectedError) {
        assertThat(createUserPage.isValidationErrorDisplayed(expectedError))
                .isTrue();

        return this;
    }
}
