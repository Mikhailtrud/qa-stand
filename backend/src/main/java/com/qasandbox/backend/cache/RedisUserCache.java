package com.qasandbox.backend.cache;

import com.qasandbox.backend.dto.user.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "qa.cache.enabled", havingValue = "true")
public class RedisUserCache implements UserCache {

    private static final Logger log = LoggerFactory.getLogger(RedisUserCache.class);
    private static final String KEY_PREFIX = "users:";

    private final RedisTemplate<String, UserResponse> redisTemplate;
    private final Duration ttl;

    public RedisUserCache(
            RedisTemplate<String, UserResponse> redisTemplate,
            @Value("${qa.cache.ttl}") Duration ttl
    ) {
        this.redisTemplate = redisTemplate;
        this.ttl = ttl;
    }

    public Optional<UserResponse> get(Long id) {
        try {
            return Optional.ofNullable(redisTemplate.opsForValue().get(key(id)));
        } catch (RuntimeException exception) {
            log.warn("Redis read failed; falling back to PostgreSQL", exception);
            return Optional.empty();
        }
    }

    public void put(UserResponse user) {
        try {
            redisTemplate.opsForValue().set(key(user.id()), user, ttl);
        } catch (RuntimeException exception) {
            log.warn("Redis write failed; continuing without cache", exception);
        }
    }

    public void evict(Long id) {
        try {
            redisTemplate.delete(key(id));
        } catch (RuntimeException exception) {
            log.warn("Redis eviction failed", exception);
        }
    }

    private String key(Long id) {
        return KEY_PREFIX + id;
    }
}
