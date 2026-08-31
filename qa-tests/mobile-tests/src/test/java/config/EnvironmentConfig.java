package config;

public final class EnvironmentConfig {
    private EnvironmentConfig() {
    }

    public static String backendUrl() {
        return ConfigValue.get("BACKEND_URL", "http://localhost:8080");
    }

    public static String adminEmail() {
        return ConfigValue.required("ADMIN_EMAIL");
    }

    public static String adminPassword() {
        return ConfigValue.required("ADMIN_PASSWORD");
    }
}
