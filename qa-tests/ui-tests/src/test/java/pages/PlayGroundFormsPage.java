package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

import java.nio.file.Path;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PlayGroundFormsPage extends BasePage{

    //Forms
    private final SelenideElement formsBlock =
            $(".playground-section:nth-child(1)");

    private final SelenideElement textInput =
            $("[data-testid='playground-text-input']");

    private final SelenideElement textarea =
            $("[data-testid='playground-textarea']");

    private final SelenideElement dateInput =
            $("[data-testid='playground-date']");

    //Forms datepicker
    private final SelenideElement openDatepicker =
            $("[data-testid='playground-date']");

    private final SelenideElement select =
            $("[data-testid='playground-select']");

    private final SelenideElement selectOption =
            $("[data-testid='playground-select'] option:nth-child");

    private final SelenideElement multiSelectOptions =
            $("[data-testid='playground-multiselect']");

    private final SelenideElement multiSelectOptionsChecked =
            $("[data-testid='playground-multiselect'] option:checked");

    private final SelenideElement fileUpload =
            $("[data-testid='playground-file-upload']");

    private final SelenideElement checkbox =
            $("[data-testid='playground-checkbox']");

    private final SelenideElement radioMale =
            $("[data-testid='radio-male']");

    private final SelenideElement radioFemale =
            $("[data-testid='radio-female']");


    //PlayGround Page
    public PlayGroundFormsPage open() {
        openPage("/playground");
        return this;
    }

    public SelenideElement formsBlock() {
        return formsBlock;
    }

    //Forms
    public PlayGroundFormsPage enterText(String text) {
        textInput.setValue(text);
        return this;
    }

    public PlayGroundFormsPage verifyTextInputValue(String expectedText) {
        $(textInput)
                .shouldHave(value(expectedText));

        return this;
    }

    public PlayGroundFormsPage enterTextarea(String text) {
        textarea.setValue(text);
        return this;
    }

    public PlayGroundFormsPage verifyTextareaValue(String expectedText) {
        $(textarea)
                .shouldHave(value(expectedText));

        return this;
    }

    public PlayGroundFormsPage enterDate(String date) {
        dateInput.setValue(date);
        return this;
    }

    public PlayGroundFormsPage verifyDateValue(String date) {
        $(dateInput)
                .shouldHave(value(date));

        return this;
    }

    public SelenideElement select() {
        return select;
    }

    public SelenideElement enterOption(long option) {
        return $("[data-testid='playground-select'] option:nth-child" + "(" + option + ")");
    }

    public PlayGroundFormsPage verifyOptionValue(String option) {
        $(select)
                .shouldHave(value(option));

        return this;
    }

    public PlayGroundFormsPage selectMultiSelectOptions(int... indexes) {
        var multiSelect = $(multiSelectOptions);

        for (int index : indexes) {
            multiSelect.selectOption(index);
        }

        return this;
    }

    public PlayGroundFormsPage verifySelectedOptions(String... expectedOptions) {
        $$("[data-testid='playground-multiselect'] option:checked")
                .shouldHave(CollectionCondition.texts(expectedOptions));

        return this;
    }

    public PlayGroundFormsPage uploadFile(Path path) {
        $(fileUpload)
                .uploadFile(path.toFile());

        return this;
    }

    public PlayGroundFormsPage verifyFileUploaded(String fileName) {
        $(fileUpload)
                .shouldHave(value(fileName));

        return this;
    }

    public SelenideElement checkbox() {
        return checkbox;
    }

    public PlayGroundFormsPage verifyCheckboxSelected() {
        $(checkbox)
                .shouldBe(selected);

        return this;
    }

    public SelenideElement getRadioMale() {return radioMale;}

    public PlayGroundFormsPage verifyMaleRadioButtonSelected() {
        $(radioMale)
                .shouldBe(selected);

        return this;
    }

    public SelenideElement getRadioFemale() {
        return radioFemale;
    }

    public PlayGroundFormsPage verifyFemaleRadioButtonSelected() {
        $(radioFemale)
                .shouldBe(selected);

        return this;
    }

}
