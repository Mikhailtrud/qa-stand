package com.qasandbox.backend.cache;

import com.qasandbox.backend.dto.user.UserResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConditionalOnProperty(name = "qa.cache.enabled", havingValue = "false", matchIfMissing = true)
public class NoOpUserCache implements UserCache {

    public Optional<UserResponse> get(Long id) {
        return Optional.empty();
    }

    public void put(UserResponse user) {
    }

    public void evict(Long id) {
    }
}
