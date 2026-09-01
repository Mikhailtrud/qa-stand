package framework.auth;

import framework.config.AppiumConfig;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

final class AdbAppStorage {
    private static final long COMMAND_TIMEOUT_SECONDS = 10;
    private static final String PREFERENCES_FILE = "shared_prefs/auth_prefs.xml";
    private static final String DEVICE_TEMP_DIRECTORY = "/data/local/tmp";

    void writeToken(String token) {
        Path localPreferences = createLocalPreferences(token);
        String devicePreferences = DEVICE_TEMP_DIRECTORY + "/qa-stand-auth-" + UUID.randomUUID() + ".xml";

        try {
            run(List.of("shell", "am", "force-stop", AppiumConfig.appPackage()));
            run(List.of("shell", "run-as", AppiumConfig.appPackage(), "mkdir", "-p", "shared_prefs"));
            run(List.of("push", localPreferences.toString(), devicePreferences));
            run(List.of(
                    "shell",
                    "cat " + devicePreferences
                            + " | run-as " + AppiumConfig.appPackage()
                            + " sh -c 'cat > " + PREFERENCES_FILE + "'"
            ));
            run(List.of("shell", "rm", "-f", devicePreferences));
        } finally {
            deleteLocalPreferences(localPreferences);
        }
    }

    void clearToken() {
        run(List.of("shell", "am", "force-stop", AppiumConfig.appPackage()));
        run(
                List.of(
                        "shell", "run-as", AppiumConfig.appPackage(),
                        "rm", "-f", PREFERENCES_FILE
                )
        );
    }

    private void run(List<String> arguments) {
        List<String> command = new ArrayList<>();
        command.add(AppiumConfig.adbPath().toString());
        if (!AppiumConfig.udid().isBlank()) {
            command.addAll(List.of("-s", AppiumConfig.udid()));
        }
        command.addAll(arguments);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            process.getOutputStream().close();

            if (!process.waitFor(COMMAND_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                process.waitFor(2, TimeUnit.SECONDS);
                throw new IllegalStateException(
                        "adb command timed out after %d seconds: %s"
                                .formatted(COMMAND_TIMEOUT_SECONDS, formatCommand(command))
                );
            }

            String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            int exitCode = process.exitValue();
            if (exitCode != 0) {
                throw new IllegalStateException(
                        "adb command failed with exit code %d: %s%n%s"
                                .formatted(exitCode, formatCommand(command), output)
                );
            }
        } catch (IOException error) {
            throw new IllegalStateException(
                    "Could not execute adb command: " + formatCommand(command),
                    error
            );
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                    "Interrupted while waiting for adb command: " + formatCommand(command),
                    error
            );
        }
    }

    private Path createLocalPreferences(String token) {
        try {
            Path file = Files.createTempFile("qa-stand-auth-", ".xml");
            Files.writeString(file, preferencesXml(token), StandardCharsets.UTF_8);
            return file;
        } catch (IOException error) {
            throw new IllegalStateException("Could not create temporary auth preferences", error);
        }
    }

    private void deleteLocalPreferences(Path file) {
        try {
            Files.deleteIfExists(file);
        } catch (IOException error) {
            throw new IllegalStateException("Could not delete temporary auth preferences: " + file, error);
        }
    }

    private String formatCommand(List<String> command) {
        return command.stream()
                .map(argument -> argument.chars().anyMatch(Character::isWhitespace)
                        ? "\"" + argument.replace("\"", "\\\"") + "\""
                        : argument)
                .reduce((left, right) -> left + " " + right)
                .orElse("");
    }

    private String preferencesXml(String token) {
        return """
                <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
                <map>
                    <string name="auth_token">%s</string>
                </map>
                """.formatted(escapeXml(token));
    }

    private String escapeXml(String value) {
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
