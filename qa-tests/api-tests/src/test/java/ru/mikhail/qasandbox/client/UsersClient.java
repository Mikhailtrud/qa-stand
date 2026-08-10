package ru.mikhail.qasandbox.client;

import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

public class UsersClient extends BaseApiClient {

    private static final String USERS_ENDPOINT = "/users";

    public GetUsersResponse[] getUsersResponse(String token) {
        return get(
                USERS_ENDPOINT,
                token,
                200,
                GetUsersResponse[].class
        );
    }
}