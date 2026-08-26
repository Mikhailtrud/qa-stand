package steps.playground;

import com.codeborne.selenide.Condition;
import steps.BaseSteps;

import java.nio.file.Path;

import static com.codeborne.selenide.Condition.visible;

public class PlayGroundFormsSteps extends BaseSteps {

    public PlayGroundFormsSteps openPlayGroundPage() {
        step("Открыть страницу PlayGround");
        playGroundPage.open();
        return this;
    }

    public PlayGroundFormsSteps verifyPlaygroundTabsVisible() {
        step("Проверить видимость блока с формами");
        playGroundPage.formsBlock().shouldBe(Condition.visible);
        return this;
    }

    public PlayGroundFormsSteps enterText(String text) {
        step("Заполнить текст в поле");
        playGroundFormsPage.enterText(text);
        return this;
    }

    public PlayGroundFormsSteps verifyTextInputValue(String expectedText) {
        step("Проверить текст в input: " + expectedText);
        playGroundFormsPage.verifyTextInputValue(expectedText);
        return this;
    }

    public PlayGroundFormsSteps enterTextarea(String text) {
        step("Заполнить ткст в поле textarea");
        playGroundFormsPage.enterTextarea(text);
        return this;
    }

    public PlayGroundFormsSteps verifyTextareaValue(String expectedText) {
        step("Проверить текст в textarea: " + expectedText);
        playGroundFormsPage.verifyTextareaValue(expectedText);
        return this;
    }

    public PlayGroundFormsSteps enterDate(String text) {
        step("Заполнить дату");
        playGroundFormsPage.enterDate(text);
        return this;
    }

    public PlayGroundFormsSteps verifyDateValue(String date) {
        step("Проверить дату в Date: " + date);
        playGroundFormsPage.verifyDateValue(date);
        return this;
    }

    public PlayGroundFormsSteps selectOption(long option) {
        step("Выбрать опцию");
        playGroundFormsPage.select().click();
        playGroundFormsPage.enterOption(option).should(visible);
        playGroundFormsPage.enterOption(option).click();
        return this;
    }

    public PlayGroundFormsSteps verifyOptionValue(String option) {
        step("Проверить опцию в Select: " + option);
        playGroundFormsPage.verifyOptionValue(option);
        return this;
    }

    public PlayGroundFormsSteps selectMultiOption(int... index) {
        step("Выбрать опцию");
        playGroundFormsPage.selectMultiSelectOptions(index);
        return this;
    }

    public PlayGroundFormsSteps verifySelectedOptions(String... expectedOptions) {
        step("Проверить выбранные опции");
        playGroundFormsPage.verifySelectedOptions(expectedOptions);
        return this;
    }

    public PlayGroundFormsSteps uploadFile(Path path) {
        step("Загрузить файл");
        playGroundFormsPage.uploadFile(path);
        return this;
    }

    public PlayGroundFormsSteps verifyFileUploaded(String fileName) {
        step("Проверить опцию в Select: " + fileName);
        playGroundFormsPage.verifyFileUploaded(fileName);
        return this;
    }

    public PlayGroundFormsSteps selectCheckbox() {
        step("Выбрать checkbox");
        playGroundFormsPage.checkbox().click();
        return this;
    }

    public PlayGroundFormsSteps verifyCheckboxSelected() {
        step("Проверить, что checkbox выбран");
        playGroundFormsPage.verifyCheckboxSelected();
        return this;
    }

    public PlayGroundFormsSteps getRadioMale() {
        step("Выбрать радио кнопку Male");
        playGroundFormsPage.getRadioMale().click();
        return this;
    }

    public PlayGroundFormsSteps verifyMaleRadioButtonSelected() {
        step("Проверить, что male radio выбран");
        playGroundFormsPage.verifyMaleRadioButtonSelected();
        return this;
    }

    public PlayGroundFormsSteps getRadioFemale() {
        step("Выбрать радио кнопку Female");
        playGroundFormsPage.getRadioFemale().click();
        return this;
    }

    public PlayGroundFormsSteps verifyFemaleRadioButtonSelected() {
        step("Проверить, что female radio выбран");
        playGroundFormsPage.verifyFemaleRadioButtonSelected();
        return this;
    }
}
