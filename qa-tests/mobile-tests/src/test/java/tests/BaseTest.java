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
import steps.PlaygroundDialogsSteps;
import steps.PlaygroundDynamicSteps;
import steps.PlaygroundFormsSteps;
import steps.PlaygroundMouseActionsSteps;
import steps.PlaygroundTableSteps;
import steps.PlaygroundTabsSteps;
import steps.UsersSteps;
import framework.utils.AllureAttachments;

public abstract class BaseTest {
    protected final LoginSteps loginSteps = new LoginSteps();
    protected final UsersSteps usersSteps = new UsersSteps();
    protected final CreateUserSteps createUserSteps = new CreateUserSteps();
    protected final EditUserSteps editUserSteps = new EditUserSteps();
    protected final PlaygroundFormsSteps playgroundFormsSteps = new PlaygroundFormsSteps();
    protected final PlaygroundDialogsSteps playgroundDialogsSteps = new PlaygroundDialogsSteps();
    protected final PlaygroundTabsSteps playgroundTabsSteps = new PlaygroundTabsSteps();
    protected final PlaygroundTableSteps playgroundTableSteps = new PlaygroundTableSteps();
    protected final PlaygroundDynamicSteps playgroundDynamicSteps = new PlaygroundDynamicSteps();
    protected final PlaygroundMouseActionsSteps playgroundMouseActionsSteps =
            new PlaygroundMouseActionsSteps();

    private final AndroidDriverProvider driverProvider = new AndroidDriverProvider();
    private final AuthStateCleaner authStateCleaner = new AuthStateCleaner();
    private Throwable primaryFailure;

    @RegisterExtension
    final AfterTestExecutionCallback failureAttachment = context ->
            context.getExecutionException().ifPresent(error -> {
                primaryFailure = error;
                AllureAttachments.attachFailureState(DriverManager.getDriverOrNull());
            });

    @BeforeEach
    void startDriver() {
        try {
            DriverManager.setDriver(driverProvider.create());
        } catch (RuntimeException | Error error) {
            primaryFailure = error;
            throw error;
        }
    }

    @AfterEach
    void stopDriver() {
        Throwable teardownFailure = null;

        try {
            authStateCleaner.cleanup();
        } catch (RuntimeException | Error error) {
            teardownFailure = error;
        }

        try {
            DriverManager.quitDriver();
        } catch (RuntimeException | Error error) {
            if (teardownFailure == null) {
                teardownFailure = error;
            } else {
                teardownFailure.addSuppressed(error);
            }
        }

        if (teardownFailure == null) {
            return;
        }

        if (primaryFailure != null) {
            primaryFailure.addSuppressed(teardownFailure);
            try {
                AllureAttachments.attachDiagnostic("Teardown failure", teardownFailure);
            } catch (RuntimeException attachmentError) {
                teardownFailure.addSuppressed(attachmentError);
            }
            return;
        }

        if (teardownFailure instanceof RuntimeException runtimeException) {
            throw runtimeException;
        }
        throw (Error) teardownFailure;
    }
}
