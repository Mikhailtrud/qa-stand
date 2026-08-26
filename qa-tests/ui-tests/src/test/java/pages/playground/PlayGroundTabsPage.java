package pages.playground;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PlayGroundTabsPage extends BasePage {

    //Elements
    private final SelenideElement tabContent =
            $("[data-testid='tab-content']");

    //Functions
    public SelenideElement tabButton(int number) {
        return $("[data-testid='tab" + number + "-button']");
    }

    public SelenideElement tabContent() {
        return $(tabContent);
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
