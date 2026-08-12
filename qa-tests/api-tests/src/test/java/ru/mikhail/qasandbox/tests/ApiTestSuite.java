package ru.mikhail.qasandbox.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.mikhail.qasandbox.tests.authTests.AuthTest;
import ru.mikhail.qasandbox.tests.usersTests.*;

@Suite
@SelectClasses({
        AuthTest.class,
        CreateUsersTests.class,
        DeleteUsersTests.class,
        EditUsersTests.class,
        GetUserInfoTests.class,
        GetUsersInfoTests.class
})
public class ApiTestSuite {
}