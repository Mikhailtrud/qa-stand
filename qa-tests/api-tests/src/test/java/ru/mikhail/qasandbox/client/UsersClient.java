package ru.mikhail.qasandbox.client;

import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;
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

    public CreateUsersResponse createUser(CreateUsersRequest request) {
        return post(
                USERS_ENDPOINT,
                request,
                201,
                CreateUsersResponse.class
        );
    }

    public void deleteUser(Integer id) {
        delete(
                USERS_ENDPOINT + "/" + id,
                200
        );
    }

    public EditUserResponse editUser(Integer id, EditUserRequest request) {
        return put(
                USERS_ENDPOINT + "/" + id,
                request,
                200,
                EditUserResponse.class
        );
    }

    public GetUserResponse getUserById(Integer id) {
        return get(
                USERS_ENDPOINT + "/" + id,
                200,
                GetUserResponse.class
        );
    }

}