package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

import java.nio.file.Path;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PlayGroundTabsPage extends BasePage{

    //Tabs*
    private final SelenideElement tabButton1 =
            $("[data-testid='tab1-button']");

    private final SelenideElement tabButton2 =
            $("[data-testid='tab2-button']");

    private final SelenideElement tabButton3 =
            $("[data-testid='tab3-button']");

    private final SelenideElement tabWindowContent =
            $("[data-testid='tab-content'] p");

}
