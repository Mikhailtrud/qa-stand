package pages;

import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    public void openPage(String url) {
        open(url);
    }

}