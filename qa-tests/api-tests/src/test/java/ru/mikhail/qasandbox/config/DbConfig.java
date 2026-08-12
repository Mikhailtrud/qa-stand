package ru.mikhail.qasandbox.config;

public final class DbConfig {

    private DbConfig() {
    }

    public static String getUrl() {
        return getProperty(
                "db.url",
                "DB_URL",
                "jdbc:postgresql://localhost:5432/qasandbox"
        );
    }

    public static String getUser() {
        return getProperty(
                "db.user",
                "DB_USER",
                "qauser"
        );
    }

    public static String getPassword() {
        return getProperty(
                "db.password",
                "DB_PASSWORD",
                "qapass"
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