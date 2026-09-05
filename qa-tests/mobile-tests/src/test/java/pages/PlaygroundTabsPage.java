package pages;

import org.openqa.selenium.By;

public final class PlaygroundTabsPage extends BasePage {
    private final By playgroundScreen = tag("playground_screen");
    private final By tabContent = tag("tab_content");

    public void selectTab(int tab) {
        scrollToElement(playgroundScreen, tabs(tab)).click();
    }

    public void verifyTabContent(String expectedText) {
        verifyText(tabContent, expectedText);
    }

    private By tabs(int tab) {
        return tag("tab_" + tab + "_button");
    }

    public boolean isDisplayed() {
        return isDisplayed(playgroundScreen);
    }
}
