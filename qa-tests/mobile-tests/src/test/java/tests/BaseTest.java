package tests;

import framework.auth.AuthStateCleaner;
import framework.driver.AndroidDriverProvider;
import framework.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;
import steps.CreateUserSteps;
import steps.EditUserSteps;
import steps.LoginSteps;
import steps.PlaygroundSteps;
import steps.UsersSteps;
import framework.utils.AllureAttachments;

public abstract class BaseTest {
    protected final LoginSteps loginSteps = new LoginSteps();
    protected final UsersSteps usersSteps = new UsersSteps();
    protected final CreateUserSteps createUserSteps = new CreateUserSteps();
    protected final EditUserSteps editUserSteps = new EditUserSteps();
    protected final PlaygroundSteps playgroundSteps = new PlaygroundSteps();

    private final AndroidDriverProvider driverProvider = new AndroidDriverProvider();
    private final AuthStateCleaner authStateCleaner = new AuthStateCleaner();

    @RegisterExtension
    final AfterTestExecutionCallback failureAttachment = context ->
            context.getExecutionException().ifPresent(error ->
                    AllureAttachments.attachFailureState(DriverManager.getDriver()));

    @BeforeEach
    void startDriver() {
        DriverManager.setDriver(driverProvider.create());
    }

    @AfterEach
    void stopDriver() {
        try {
            authStateCleaner.cleanup();
        } finally {
            DriverManager.quitDriver();
        }
    }
}
