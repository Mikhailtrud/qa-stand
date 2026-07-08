package com.qasandbox.backend.mapper;

import com.qasandbox.backend.dto.user.CreateUserRequest;
import com.qasandbox.backend.dto.user.UpdateUserRequest;
import com.qasandbox.backend.dto.user.UserResponse;
import com.qasandbox.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(CreateUserRequest request) {

        User user = new User();

        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(request.password());
        user.setRole(request.role());

        return user;

    }

    public void updateEntity(
            User user,
            UpdateUserRequest request
    ) {

        user.setEmail(request.email());
        user.setName(request.name());
        user.setRole(request.role());

    }

    public UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole()
        );

    }

}