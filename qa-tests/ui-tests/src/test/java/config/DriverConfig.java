package config;

import com.codeborne.selenide.Configuration;

public class DriverConfig {

    private DriverConfig() {
    }

    public static void configure() {

        Configuration.baseUrl = TestConfig.BASE_URL;

        Configuration.browser = TestConfig.BROWSER;

        Configuration.browserSize = "1920x1080";

        Configuration.pageLoadStrategy = "eager";

        Configuration.timeout = 10000;

    }

}