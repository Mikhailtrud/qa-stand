package steps;

import com.codeborne.selenide.Condition;

public class PlayGroundDynamicMouseSteps extends BaseSteps {

    public PlayGroundDynamicMouseSteps openPlayGroundPage() {
        step("Открыть страницу PlayGround");
        playGroundDynamicMousePage.open();
        return this;
    }

    public PlayGroundDynamicMouseSteps verifyPlaygroundDynamicElementsVisible() {
        step("Проверить видимость блока с таблицей");
        playGroundDynamicMousePage.dynamicElementsBlock().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundDynamicMouseSteps clickOnStartLoaderButton() {
        step("Нажать на кнопку старта лоадера");
        playGroundDynamicMousePage.startLoaderButton().click();
        return this;
    }

    public PlayGroundDynamicMouseSteps verifyPlaygroundDynamicLoaderVisible() {
        step("Проверить видимость лоадера");
        playGroundDynamicMousePage.dynamicElementsBlock().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundDynamicMouseSteps verifyPLoaderText(String text) {
        step("Проверить текст лоадера");
        playGroundDynamicMousePage.verifyLoaderText(text);
        return this;
    }

    public PlayGroundDynamicMouseSteps clickOnShowDelayedButton() {
        step("Нажать на кнопку Show Delayed button");
        playGroundDynamicMousePage.showDelayedButton().click();
        return this;
    }

    public PlayGroundDynamicMouseSteps verifyPlaygroundDelayedButtonVisible() {
        step("Проверить видимость кнопки с задержкой");
        playGroundDynamicMousePage.delayedButton().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundDynamicMouseSteps verifyDelayedButtonText(String text) {
        step("Проверить текст кнопки с задержкой");
        playGroundDynamicMousePage.verifyDelayedButtonText(text);
        return this;
    }

    public PlayGroundDynamicMouseSteps verifyDynamicsElementsBlockText(String text) {
        step("Проверить текст блока динамических элементов");
        playGroundDynamicMousePage.verifyDynamicsElementsBlockText(text);
        return this;
    }

}
