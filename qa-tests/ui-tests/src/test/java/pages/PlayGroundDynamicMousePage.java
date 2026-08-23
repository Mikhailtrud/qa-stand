package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PlayGroundDynamicMousePage extends BasePage{


    //PlayGround Page
    public PlayGroundDynamicMousePage open() {
        openPage("/playground");
        return this;
    }

    public SelenideElement dynamicElementsBlock() {
        return dynamicElementsBlock;
    }

    private final SelenideElement dynamicElementsBlock =
            $(".playground-section:nth-child(5)");

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


    //Dynamic Elements Functions
    public SelenideElement startLoaderButton() {
        return startLoaderButton;
    }

    public PlayGroundDynamicMousePage verifyLoaderText(String text) {
        $(loaderElementText)
                .shouldHave(text(text));
        return this;
    }

    public SelenideElement showDelayedButton() {
        return showDelayedButton;
    }

    public SelenideElement delayedButton() {
        return delayedButton;
    }

    public PlayGroundDynamicMousePage verifyDelayedButtonText(String text) {
        $(delayedButton)
                .shouldHave(text(text));
        return this;
    }

    public PlayGroundDynamicMousePage verifyDynamicsElementsBlockText(String text) {
        $(dynamicElementsHidden)
                .shouldHave(text(text));
        return this;
    }

}
