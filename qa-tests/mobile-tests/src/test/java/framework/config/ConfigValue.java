package framework.config;

final class ConfigValue {
    private ConfigValue() {
    }

    static String get(String name, String defaultValue) {
        String systemValue = System.getProperty(name);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        String environmentValue = System.getenv(name);
        return environmentValue == null || environmentValue.isBlank() ? defaultValue : environmentValue;
    }

    static String required(String name) {
        String configured = get(name, "");
        if (configured.isBlank()) {
            throw new IllegalStateException(name + " must be provided as an environment variable or system property");
        }
        return configured;
    }
}
