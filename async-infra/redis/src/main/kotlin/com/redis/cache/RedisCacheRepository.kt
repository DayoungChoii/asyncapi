package com.redis.cache

import com.fasterxml.jackson.databind.ObjectMapper
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import java.time.Duration

class RedisCacheRepository(
    private val redisCommands: RedisCoroutinesCommands<String, String>,
    private val objectMapper: ObjectMapper
) : CacheRepository {
    override suspend fun <T> get(key: String, clazz: Class<T>): T? {
        val result = redisCommands.get(key)
        return result?.let { objectMapper.readValue(it, clazz) }
    }

    override suspend fun <T> set(key: String, value: T, ttl: Duration?) {
        val json = objectMapper.writeValueAsString(value)
        if (ttl != null) {
            redisCommands.setex(key, ttl.seconds, json)
        } else {
            redisCommands.set(key, json)
        }
    }

    override suspend fun delete(key: String) {
        redisCommands.del(key)
    }

}