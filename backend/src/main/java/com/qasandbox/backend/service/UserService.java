package com.qasandbox.backend.service;

import com.qasandbox.backend.dto.user.CreateUserRequest;
import com.qasandbox.backend.dto.user.UpdateUserRequest;
import com.qasandbox.backend.dto.user.UserResponse;
import com.qasandbox.backend.dto.external.ExternalProfileResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    ExternalProfileResponse getExternalProfile(Long id);

    void deleteUser(Long id);

}
