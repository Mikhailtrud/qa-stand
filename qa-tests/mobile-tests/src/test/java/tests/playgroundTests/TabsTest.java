package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.BaseTest;


@Epic("QA Stand Mobile")
@Feature("QA Playground")
@Story("Tabs")
public class TabsTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        usersSteps.openPlayground();
        playgroundTabsSteps.verifyPlaygroundDisplayed();
    }

    @ParameterizedTest(name = "Tab {0} -> {1}")
    @Description("Selecting a tab displays its corresponding content")
    @CsvSource({
            "1, Content Tab 1",
            "2, Content Tab 2",
            "3, Content Tab 3"
    })
    void selectedTabDisplaysExpectedContent(int tabNumber, String expectedText) {
        playgroundTabsSteps
                .selectTab(tabNumber)
                .verifyTabContent(expectedText);
    }
}
