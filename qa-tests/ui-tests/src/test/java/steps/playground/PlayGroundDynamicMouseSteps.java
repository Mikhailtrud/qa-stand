package steps.playground;

import com.codeborne.selenide.Condition;
import steps.BaseSteps;
import io.qameta.allure.Step;

public class PlayGroundDynamicMouseSteps extends BaseSteps {

    @Step("Open the Playground page")
    public PlayGroundDynamicMouseSteps openPlayGroundPage() {
        playGroundPage.open();
        return this;
    }

    @Step("Verify that the Dynamic Elements section is visible")
    public PlayGroundDynamicMouseSteps verifyPlaygroundDynamicElementsVisible() {
        playGroundPage.dynamicElementsBlock().shouldBe(Condition.visible);
        return this;
    }

    @Step("Start the loader")
    public PlayGroundDynamicMouseSteps clickOnStartLoaderButton() {
        playGroundDynamicMousePage.startLoaderButton().click();
        return this;
    }

    @Step("Verify loader text: {text}")
    public PlayGroundDynamicMouseSteps verifyLoaderText(String text) {
        playGroundDynamicMousePage.verifyLoaderText(text);
        return this;
    }

    @Step("Verify that the loader is visible")
    public PlayGroundDynamicMouseSteps verifyLoaderVisible() {
        playGroundDynamicMousePage.verifyLoaderVisible();
        return this;
    }

    @Step("Show the delayed button")
    public PlayGroundDynamicMouseSteps clickOnShowDelayedButton() {
        playGroundDynamicMousePage.showDelayedButton().click();
        return this;
    }

    @Step("Verify that the delayed button is visible")
    public PlayGroundDynamicMouseSteps verifyPlaygroundDelayedButtonVisible() {
        playGroundDynamicMousePage.delayedButton().shouldBe(Condition.visible);
        return this;
    }

    @Step("Verify delayed button text: {text}")
    public PlayGroundDynamicMouseSteps verifyDelayedButtonText(String text) {
        playGroundDynamicMousePage.verifyDelayedButtonText(text);
        return this;
    }

    @Step("Verify dynamic element text: {text}")
    public PlayGroundDynamicMouseSteps verifyDynamicsElementsBlockText(String text) {
        playGroundDynamicMousePage.verifyDynamicsElementsBlockText(text);
        return this;
    }

    @Step("Verify that the hidden element is visible")
    public PlayGroundDynamicMouseSteps verifyHiddenElementVisible() {
        playGroundDynamicMousePage.verifyHiddenElementVisible();
        return this;
    }

}
