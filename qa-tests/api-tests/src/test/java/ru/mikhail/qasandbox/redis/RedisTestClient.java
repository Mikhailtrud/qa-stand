package ru.mikhail.qasandbox.redis;

import redis.clients.jedis.JedisPooled;
import ru.mikhail.qasandbox.config.IntegrationConfig;

public class RedisTestClient implements AutoCloseable {

    private static final String USER_KEY_PREFIX = "users:";
    private final JedisPooled jedis = new JedisPooled(
            IntegrationConfig.getRedisHost(),
            IntegrationConfig.getRedisPort()
    );

    public String userKey(Integer userId) {
        return USER_KEY_PREFIX + userId;
    }

    public boolean userExists(Integer userId) {
        return jedis.exists(userKey(userId));
    }

    public String getUser(Integer userId) {
        return jedis.get(userKey(userId));
    }

    @Override
    public void close() {
        jedis.close();
    }
}
