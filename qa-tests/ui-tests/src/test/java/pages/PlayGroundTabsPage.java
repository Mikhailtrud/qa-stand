package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PlayGroundTabsPage extends BasePage{

    public PlayGroundTabsPage open() {
        openPage("/playground");
        return this;
    }

    public SelenideElement tabsBlock() {
        return tabsBlock;
    }

    private final SelenideElement tabsBlock =
            $(".playground-section:nth-child(3)");

    //Tabs*

    public SelenideElement tabButton(int number) {
        return $("[data-testid='tab" + number + "-button']");
    }

    public SelenideElement tabContent() {
        return $("[data-testid='tab-content']");
    }

    public PlayGroundTabsPage clickTab(int number) {
        tabButton(number).click();
        return this;
    }

    public PlayGroundTabsPage verifyTabContent(String expectedText) {
        tabContent().shouldHave(text(expectedText));
        return this;
    }

}
