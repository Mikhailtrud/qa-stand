package com.qasandbox.backend.cache;

import com.qasandbox.backend.dto.user.UserResponse;

import java.util.Optional;

public interface UserCache {

    Optional<UserResponse> get(Long id);

    void put(UserResponse user);

    void evict(Long id);
}
