package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

import java.nio.file.Path;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PlayGroundDynamicMousePage extends BasePage{


    //Dynamic Elements*
    private final SelenideElement startLoaderButton =
            $("[data-testid='start-loader-button']");

    private final SelenideElement loaderElement =
            $("[data-testid='loader']");

    private final SelenideElement showDelayedButton =
            $("[data-testid='show-delayed-button']");

    private final SelenideElement delayedButton =
            $("[data-testid='delayed-button']");

    private final SelenideElement dynamicElementsHidden =
            $("[data-testid='hidden-element']");

    //Mouse Actions
    private final SelenideElement hoverButton =
            $("[data-testid='hover-button']");

    private final SelenideElement doubleClickButton =
            $("[data-testid='double-click-button']");

    private final SelenideElement rightClickButton =
            $("[data-testid='right-click-button']");

    private final SelenideElement actionResult =
            $("[data-testid='mouse-action-result']");

    private final SelenideElement t =
            $("[]");



    //PlayGround Page
    public PlayGroundDynamicMousePage open() {
        openPage("/playground");
        return this;
    }


}
