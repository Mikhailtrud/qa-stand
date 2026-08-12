package ru.mikhail.qasandbox.config;

public final class ApiConfig {

    private ApiConfig() {
    }

    public static String getBaseUrl() {
        return getProperty(
                "baseUrl",
                "BASE_URL",
                "http://localhost:8080"
        );
    }

    private static String getProperty(String propertyName,
                                      String environmentVariable,
                                      String defaultValue) {

        String value = System.getProperty(propertyName);

        if (value == null || value.isBlank()) {
            value = System.getenv(environmentVariable);
        }

        return (value == null || value.isBlank())
                ? defaultValue
                : value;
    }
}