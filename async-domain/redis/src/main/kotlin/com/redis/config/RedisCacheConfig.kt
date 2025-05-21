package com.redis.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.RedisSerializer

@Configuration
class RedisCacheConfig {

    @Bean
    fun cacheManager(redisConnectionFactory: RedisConnectionFactory): CacheManager {
        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration())
            .build()
    }

    @Bean
    fun redisCacheConfiguration(): RedisCacheConfiguration {
        return RedisCacheConfiguration.defaultCacheConfig()
            .disableCachingNullValues()
            .serializeKeysWith(keySerializer)
            .serializeValuesWith(valueSerializer)
    }

    private val keySerializer
        get() = RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string())

    private val valueSerializer
        get() = RedisSerializationContext.SerializationPair.fromSerializer(
            GenericJackson2JsonRedisSerializer(
                redisObjectMapper
            )
        )

    private val redisObjectMapper
        get() = ObjectMapper()
            .registerModules(KotlinModule.Builder().build(), JavaTimeModule())
            .activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder().allowIfBaseType(Any::class.java).build(),
                ObjectMapper.DefaultTyping.NON_FINAL)
}