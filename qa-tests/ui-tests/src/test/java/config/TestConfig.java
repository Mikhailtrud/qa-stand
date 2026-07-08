package config;

public class TestConfig {

    private TestConfig() {
    }

    public static final String BASE_URL =
            System.getProperty("baseUrl", "http://localhost:5173");

    public static final String BROWSER =
            System.getProperty("browser", "chrome");

    public static final boolean REMOTE =
            Boolean.parseBoolean(System.getProperty("remote", "false"));

    public static final String REMOTE_URL =
            System.getProperty(
                    "remoteUrl",
                    "http://localhost:4444/wd/hub"
            );

}