package ru.mikhail.qasandbox.config;

public final class UserDataConfig {

    private UserDataConfig() {
    }

    // Admin Data

    public static String getAdminEmail() {
        return getProperty(
                "admin.email",
                "ADMIN_EMAIL",
                "test@email.com"
        );
    }

    public static String getAdminPassword() {
        return getProperty(
                "admin.password",
                "ADMIN_PASSWORD",
                "admin123"
        );
    }

    public static String getAdminRole() {
        return getProperty(
                "admin.role",
                "ADMIN_ROLE",
                "ADMIN"
        );
    }

    public static final String ADMIN_NAME = "string";

    // User Data

    public static final String USER_NAME = "User";

    public static String getUserEmail() {
        return getProperty(
                "user.email",
                "USER_EMAIL",
                "user@email.com"
        );
    }

    public static String getUserPassword() {
        return getProperty(
                "user.password",
                "USER_PASSWORD",
                "user123"
        );
    }

    public static String getUserRole() {
        return getProperty(
                "user.role",
                "USER_ROLE",
                "USER"
        );
    }

    // Edit User Data

    public static final String EDIT_USER_NAME = "EditUser";

    public static String getEditUserEmail() {
        return getProperty(
                "edit.user.email",
                "EDIT_USER_EMAIL",
                "edituser@email.com"
        );
    }

    public static String getEditUserPassword() {
        return getProperty(
                "edit.user.password",
                "EDIT_USER_PASSWORD",
                "user123"
        );
    }

    public static String getEditUserRole() {
        return getProperty(
                "edit.user.role",
                "EDIT_USER_ROLE",
                "USER"
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