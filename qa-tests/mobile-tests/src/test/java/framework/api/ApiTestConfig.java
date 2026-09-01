package framework.api;

final class ApiTestConfig {

    private ApiTestConfig() {
    }

    static String baseUrl() {
        return System.getProperty("apiBaseUrl", "http://localhost:8080");
    }

    static String adminEmail() {
        return System.getProperty("admin.email", "test@email.com");
    }

    static String adminPassword() {
        return System.getProperty("admin.password", "admin123");
    }
}
