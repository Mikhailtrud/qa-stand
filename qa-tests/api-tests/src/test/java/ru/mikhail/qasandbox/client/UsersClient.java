package ru.mikhail.qasandbox.client;

import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

public class UsersClient extends BaseApiClient {

    private static final String USERS_ENDPOINT = "/users";

    public GetUsersResponse[] getUsersResponse() {
        return get(
                USERS_ENDPOINT,
                200,
                GetUsersResponse[].class
        );
    }
}