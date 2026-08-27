package pages.playground;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class PlayGroundPage extends BasePage {
    //Elements
    private final SelenideElement formsBlock =
            $("[data-testid='playground-forms-section']");

    private final SelenideElement javaScript =
            $("[data-testid='playground-javascript-section']");

    private final SelenideElement tabsBlock =
            $("[data-testid='playground-tabs-section']");

    private final SelenideElement tablesBlock =
            $("[data-testid='playground-tables-section']");

    private final SelenideElement dynamicElementsBlock =
            $("[data-testid='playground-dynamic-section']");

    private final SelenideElement mouseActions =
            $("[data-testid='playground-mouse-section']");

    //Functions
    public PlayGroundPage open() {
        openPage("/playground");
        return this;
    }

    public SelenideElement formsBlock() {
        return formsBlock;
    }

    public SelenideElement javaScript() {
        return javaScript;
    }

    public SelenideElement tabsBlock() {
        return tabsBlock;
    }

    public SelenideElement tablesBlock() {
        return tablesBlock;
    }

    public SelenideElement dynamicElementsBlock() {
        return dynamicElementsBlock;
    }

    public SelenideElement mouseActionsBlock() {
        return mouseActions;
    }

}
