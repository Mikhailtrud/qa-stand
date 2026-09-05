package steps;

import io.qameta.allure.Step;

public final class EditUserSteps extends BaseSteps {

    @Step("Replace all editable user fields")
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

    @Step("Save the edited user")
    public EditUserSteps save() {
        editUserPage.save();
        return this;
    }

    @Step("Cancel editing the user")
    public EditUserSteps cancelEdit() {
        editUserPage.cancelEdit();
        return this;
    }

}
