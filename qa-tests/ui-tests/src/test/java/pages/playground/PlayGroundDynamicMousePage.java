package pages.playground;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PlayGroundDynamicMousePage extends BasePage {

    //Elements
    //Dynamic Elements*
    private final SelenideElement startLoaderButton =
            $("[data-testid='start-loader-button']");

    private final SelenideElement loaderElementText =
            $("[data-testid='loader']");

    private final SelenideElement showDelayedButton =
            $("[data-testid='show-delayed-button']");

    private final SelenideElement delayedButton =
            $("[data-testid='delayed-button']");

    private final SelenideElement dynamicElementsHidden =
            $("[data-testid='hidden-element']");

    //Functions
    //Dynamic Elements Functions
    public SelenideElement startLoaderButton() {
        return startLoaderButton;
    }

    public PlayGroundDynamicMousePage verifyLoaderText(String text) {
        loaderElementText.shouldHave(text(text));
        return this;
    }

    public PlayGroundDynamicMousePage verifyLoaderVisible() {
        loaderElementText.shouldBe(visible);
        return this;
    }

    public SelenideElement showDelayedButton() {
        return showDelayedButton;
    }

    public SelenideElement delayedButton() {
        return delayedButton;
    }

    public PlayGroundDynamicMousePage verifyDelayedButtonText(String text) {
        delayedButton.shouldHave(text(text));
        return this;
    }

    public PlayGroundDynamicMousePage verifyDynamicsElementsBlockText(String text) {
        dynamicElementsHidden.shouldHave(text(text));
        return this;
    }

    public PlayGroundDynamicMousePage verifyHiddenElementVisible() {
        dynamicElementsHidden.shouldBe(visible);
        return this;
    }

}
