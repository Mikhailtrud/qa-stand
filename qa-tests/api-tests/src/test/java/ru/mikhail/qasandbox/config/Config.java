package ru.mikhail.qasandbox.config;

public final class Config {

    private Config() {
    }

    //Admin Data
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

    public static String adminName = "string";

    //User Data
    public static String getUserRole() {
        return getProperty("user.role", "USER_ROLE", "USER");
    }

    public static String getUserEmail() {
        return getProperty("user.email", "USER_EMAIL", "user@email.com");
    }

    public static String getUserPassword() {
        return getProperty("user.password", "USER_PASSWORD", "user123");
    }

    public static String userName = "User";

    //Edit User Data
    public static String editUserRole() {
        return getProperty("edit.user.role", "EDIT_USER_ROLE", "USER");
    }

    public static String editUserEmail() {
        return getProperty("edit.user.email", "EDIT_USER_EMAIL", "edituser@email.com");
    }

    public static String editUserPassword() {
        return getProperty("edit.user.password", "EDIT_USER_PASSWORD", "user123");
    }

    public static String editUserName = "EditUser";

    //DB data
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