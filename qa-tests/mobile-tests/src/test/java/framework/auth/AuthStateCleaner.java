package framework.auth;

public final class AuthStateCleaner {
    private final AdbAppStorage appStorage = new AdbAppStorage();

    public void cleanup() {
        appStorage.clearToken();
    }
}
