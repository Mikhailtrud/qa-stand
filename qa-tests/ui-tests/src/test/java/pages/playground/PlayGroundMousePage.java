package pages.playground;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PlayGroundMousePage extends BasePage {

    //Elements
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