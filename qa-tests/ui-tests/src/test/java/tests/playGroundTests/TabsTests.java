package tests.playGroundTests;

import config.AuthenticatedTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Epic("QA Stand")
@Feature("Playground: Tabs")
@Story("Tab navigation")
public class TabsTests extends AuthenticatedTest {

    @BeforeEach
    void setUp() {
        playGroundTabsSteps
                .openPlayGroundPage()
                .verifyPlaygroundTabsVisible();
    }

    @ParameterizedTest(name = "Tab {0} -> {1}")
    @CsvSource({
            "1, Content Tab 1",
            "2, Content Tab 2",
            "3, Content Tab 3"
    })
    void tabsTest(int tabNumber, String expectedText) {
        playGroundTabsSteps
                .selectTab(tabNumber)
                .verifyTabContent(expectedText);
    }
}
