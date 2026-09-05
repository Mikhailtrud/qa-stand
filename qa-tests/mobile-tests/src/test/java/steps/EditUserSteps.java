package steps;

import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public final class EditUserSteps extends BaseSteps {

    public EditUserSteps replaceForm(String name, String email, String role) {
        editUserPage.setName(name).setEmail(email).setRole(role);
        return this;
    }

    @Step("Edit user name")
    public EditUserSteps editName(String name) {
        editUserPage.setName(name);
        return this;
    }

    @Step("Edit user email")
    public EditUserSteps editEmail(String email) {
        editUserPage.setEmail(email);
        return this;
    }

    @Step("Edit user role")
    public EditUserSteps editRole(String role) {
        editUserPage.setRole(role);
        return this;
    }

    @Step("Save the new user")
    public EditUserSteps save() {
        editUserPage.save();
        return this;
    }

    @Step("Cancel save the user edition")
    public EditUserSteps cancelEdit() {
        editUserPage.cancelEdit();
        return this;
    }

    @Step("Save button should not be clickable")
    public EditUserSteps verifySaveButtonClickable() {
        assertThat(editUserPage.isSaveButtonClickable()).isTrue();
        return this;
    }
}
