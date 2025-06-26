package com.redis.chache

import com.fasterxml.jackson.databind.ObjectMapper
import com.redis.cache.CacheRepository
import com.redis.cache.RedisCacheRepository
import io.lettuce.core.RedisClient
import io.lettuce.core.api.coroutines
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import java.math.BigDecimal
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisCacheRepositoryTest {

    private lateinit var redisClient: RedisClient
    private lateinit var redis: RedisCoroutinesCommands<String, String>
    private lateinit var cacheRepository: CacheRepository
    private val objectMapper = ObjectMapper()

    data class ProductDto(
        val id: Long = 0,
        val price: BigDecimal = BigDecimal.valueOf(0)
    )

    @BeforeAll
    fun setup() {
        redisClient = RedisClient.create("redis://localhost:6379")
        redis = redisClient.connect().coroutines()
        cacheRepository = RedisCacheRepository(redis, objectMapper)
    }

    @AfterAll
    fun teardown() {
        redisClient.shutdown()
    }

    @Test
    fun `set and get should store and retrieve value`() = runBlocking {
        // given
        val key = "test:sample"
        val value = ProductDto(1, BigDecimal.valueOf(1_000))

        // when
        cacheRepository.set(key, value, Duration.ofMinutes(1))
        val result = cacheRepository.get(key, ProductDto::class.java)

        // then
        assertEquals(value, result)
    }

    @Test
    fun `delete should remove the value`() = runBlocking {
        // given
        val key = "test:sample2"
        val value = ProductDto(2, BigDecimal.valueOf(2_000))

        // when
        cacheRepository.set(key, value)
        cacheRepository.delete(key)
        val result = cacheRepository.get(key, ProductDto::class.java)

        // then
        assertNull(result)
    }
}
