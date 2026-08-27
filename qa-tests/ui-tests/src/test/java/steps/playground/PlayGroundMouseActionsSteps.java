package steps.playground;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import steps.BaseSteps;

public class PlayGroundMouseActionsSteps extends BaseSteps {

    @Step("Open the Playground page")
    public PlayGroundMouseActionsSteps openPlayGroundPage() {
        playGroundPage.open();
        return this;
    }

    @Step("Verify that the Mouse Actions section is visible")
    public PlayGroundMouseActionsSteps verifyPlaygroundMouseActionsVisible() {
        playGroundPage.mouseActionsBlock().shouldBe(Condition.visible);
        return this;
    }

    @Step("Hover over the element")
    public PlayGroundMouseActionsSteps hoverElement() {
        playGroundMousePage.hoverElement();
        return this;
    }

    @Step("Double-click the element")
    public PlayGroundMouseActionsSteps doubleClickElement() {
        playGroundMousePage.doubleClickElement();
        return this;
    }

    @Step("Right-click the element")
    public PlayGroundMouseActionsSteps rightClickElement() {
        playGroundMousePage.rightClickElement();
        return this;
    }

    @Step("Verify mouse action result: {text}")
    public PlayGroundMouseActionsSteps verifyMouseActionResult(String text) {
        playGroundMousePage.verifyMouseActionResult(text);
        return this;
    }

}
