package tests.playgroundTests;

import framework.auth.IntentAuthProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.BaseTest;


public class TabsTest extends BaseTest {
    private final IntentAuthProvider authProvider = new IntentAuthProvider();

    @BeforeEach
    void setUp() {
        authProvider.authorizeAsAdmin();
        playgroundSteps
                .openPlayground()
                .verifyPlaygroundDisplayed();
    }

    @ParameterizedTest(name = "Tab {0} -> {1}")
    @CsvSource({
            "1, Content Tab 1",
            "2, Content Tab 2",
            "3, Content Tab 3"
    })
    void tabsTest(int tabNumber, String expectedText) {
        playgroundSteps
                .selectTabs(tabNumber)
                .verifyTabContent(expectedText);
    }
}
