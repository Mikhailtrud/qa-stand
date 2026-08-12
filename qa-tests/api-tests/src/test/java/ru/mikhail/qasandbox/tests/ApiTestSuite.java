package ru.mikhail.qasandbox.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

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