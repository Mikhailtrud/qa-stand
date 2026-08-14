package ru.mikhail.qasandbox.assertions;

import java.util.Objects;

import org.assertj.core.api.AbstractAssert;
import ru.mikhail.qasandbox.client.ApiResponse;

public final class ApiResponseAssert<T>
        extends AbstractAssert<ApiResponseAssert<T>, ApiResponse<T>> {

    private ApiResponseAssert(ApiResponse<T> actual) {
        super(actual, ApiResponseAssert.class);
    }

    public static <T> ApiResponseAssert<T> assertThat(ApiResponse<T> actual) {
        return new ApiResponseAssert<>(actual);
    }

    public ApiResponseAssert<T> hasStatusCode(int expected) {
        isNotNull();

        int actualStatusCode = actual.statusCode();

        if (actualStatusCode != expected) {
            failWithMessage(
                    "Expected status code to be <%s> but was <%s>",
                    expected,
                    actualStatusCode
            );
        }

        return this;
    }

    public ApiResponseAssert<T> hasErrorMessage(String expected) {
        isNotNull();

        String actualMessage = actual.errorBody().message();

        if (!Objects.equals(actualMessage, expected)) {
            failWithMessage(
                    "Expected error message to be <%s> but was <%s>",
                    expected,
                    actualMessage
            );
        }

        return this;
    }
}
