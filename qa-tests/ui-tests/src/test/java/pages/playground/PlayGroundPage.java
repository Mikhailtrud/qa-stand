package pages.playground;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class PlayGroundPage extends BasePage {
    //Elements
    private final SelenideElement formsBlock =
            $(".playground-section:nth-child(1)");

    private final SelenideElement javaScript =
            $(".playground-section:nth-child(2)");

    private final SelenideElement tabsBlock =
            $(".playground-section:nth-child(3)");

    private final SelenideElement tablesBlock =
            $(".playground-section:nth-child(4)");

    private final SelenideElement dynamicElementsBlock =
            $(".playground-section:nth-child(5)");

    private final SelenideElement mouseActions =
            $(".playground-section:nth-child(6)");

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
