package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PlayGroundMousePage extends BasePage {

    //PlayGround Page
    public PlayGroundMousePage open() {
        openPage("/playground");
        return this;
    }

    public SelenideElement mouseActionsBlock() {
        return dynamicElementsBlock;
    }

    private final SelenideElement dynamicElementsBlock =
            $(".playground-section:nth-child(6)");

    private final SelenideElement hoverElement =
            $("[data-testid='hover-button']");

    private final SelenideElement doubleClickElement =
            $("[data-testid='double-click-button']");

    private final SelenideElement rightClickElement =
            $("[data-testid='right-click-button']");

    private final SelenideElement actionResult =
            $("[data-testid='mouse-action-result']");


    //Functions
    public PlayGroundMousePage hoverElement() {
        hoverElement.hover();
        return this;
    }

    public PlayGroundMousePage doubleClickElement() {
        doubleClickElement.doubleClick();
        return this;
    }

    public PlayGroundMousePage rightClickElement() {
        rightClickElement.contextClick();
        return this;
    }

    public PlayGroundMousePage verifyMouseActionResult(String text) {
        $(actionResult)
                .shouldHave(text(text));
        return this;
    }
}