package ru.mikhail.qasandbox.config;

public final class IntegrationConfig {

    private IntegrationConfig() {
    }

    public static String getAuditServiceUrl() {
        return value("audit.service.url", "AUDIT_SERVICE_URL", "http://localhost:8090");
    }

    public static String getRedisHost() {
        return value("redis.host", "REDIS_HOST", "localhost");
    }

    public static int getRedisPort() {
        return Integer.parseInt(value("redis.port", "REDIS_PORT", "6379"));
    }

    private static String value(String propertyName, String environmentVariable, String defaultValue) {
        String configured = System.getProperty(propertyName);
        if (configured == null || configured.isBlank()) {
            configured = System.getenv(environmentVariable);
        }
        return configured == null || configured.isBlank() ? defaultValue : configured;
    }
}
