package ru.mikhail.qasandbox.client;

import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

public class UsersClient extends BaseApiClient {

    private static final String USERS_ENDPOINT = "/users";

    public ApiResponse<GetUsersResponse[]> getUsersResponse() {
        return get(
                USERS_ENDPOINT,
                GetUsersResponse[].class
        );
    }

    public ApiResponse<CreateUsersResponse> createUser(CreateUsersRequest request) {
        return post(
                USERS_ENDPOINT,
                request,
                CreateUsersResponse.class
        );
    }

    public ApiResponse<Void> deleteUser(Integer id) {
        return delete(
                USERS_ENDPOINT + "/" + id
        );
    }

    public ApiResponse<Void> deleteUserByRawId(String id) {
        return delete(
                USERS_ENDPOINT + "/" + id
        );
    }

    public ApiResponse<EditUserResponse> editUser(
            Integer id,
            EditUserRequest request
    ) {
        return put(
                USERS_ENDPOINT + "/" + id,
                request,
                EditUserResponse.class
        );
    }

    public ApiResponse<GetUserResponse> getUserById(Integer id) {
        return get(
                USERS_ENDPOINT + "/" + id,
                GetUserResponse.class
        );
    }

    public ApiResponse<Void> getUserByRawId(String id) {
        return get(
                USERS_ENDPOINT + "/" + id,
                Void.class
        );
    }
}
