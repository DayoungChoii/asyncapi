package com.redis.cache.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.redis.cache.CacheRepository
import com.redis.cache.RedisCacheRepository
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.coroutines
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedisLettuceConfig {

    @Value("\${redis.url}")
    lateinit var redisUrl: String

    @Bean
    fun redisClient(): RedisClient =
        RedisClient.create(redisUrl)

    @Bean
    fun redisConnection(redisClient: RedisClient): StatefulRedisConnection<String, String> =
        redisClient.connect()

    @Bean
    fun redisCoroutinesCommands(
        connection: StatefulRedisConnection<String, String>
    ): RedisCoroutinesCommands<String, String> {
        return connection.coroutines()
    }

    @Bean
    fun cacheRepository(redis: RedisCoroutinesCommands<String, String>): CacheRepository =
        RedisCacheRepository(redis, ObjectMapper())
}