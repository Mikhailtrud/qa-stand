package ru.mikhail.qasandbox.config;

public final class Config {

    private Config() {
    }

    public static String getBaseUrl() {
        return getProperty("baseUrl", "BASE_URL", "http://localhost:8080");
    }

    public static String getAdminEmail() {
        return getProperty("admin.email", "ADMIN_EMAIL", "test@email.com");
    }

    public static String getAdminPassword() {
        return getProperty("admin.password", "ADMIN_PASSWORD", "admin123");
    }

    public static String getAdminRole() {
        return getProperty("admin.role", "ADMIN_ROLE", "ADMIN");
    }

    public static String getDbUrl() {
        return getProperty(
                "db.url",
                "DB_URL",
                "jdbc:postgresql://localhost:5432/qasandbox"
        );
    }

    public static String getDbUser() {
        return getProperty(
                "db.user",
                "DB_USER",
                "qauser"
        );
    }

    public static String getDbPassword() {
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