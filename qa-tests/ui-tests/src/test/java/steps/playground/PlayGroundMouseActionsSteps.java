package steps.playground;

import com.codeborne.selenide.Condition;
import steps.BaseSteps;

public class PlayGroundMouseActionsSteps extends BaseSteps {

    public PlayGroundMouseActionsSteps openPlayGroundPage() {
        step("Открыть страницу PlayGround");
        playGroundPage.open();
        return this;
    }

    public PlayGroundMouseActionsSteps verifyPlaygroundMouseActionsVisible() {
        step("Проверить видимость блока с таблицей");
        playGroundPage.mouseActionsBlock().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundMouseActionsSteps hoverElement() {
        step("Навести курсор на элемент");
        playGroundMousePage.hoverElement();
        return this;
    }

    public PlayGroundMouseActionsSteps doubleClickElement() {
        step("Выполнить двойной клик");
        playGroundMousePage.doubleClickElement();
        return this;
    }

    public PlayGroundMouseActionsSteps rightClickElement() {
        step("Выполнить клик правой кнопкой мыши");
        playGroundMousePage.rightClickElement();
        return this;
    }

    public PlayGroundMouseActionsSteps verifyMouseActionResult(String text) {
        step("Проверить результат в блоке Mouse Actions");
        playGroundMousePage.verifyMouseActionResult(text);
        return this;
    }

}
