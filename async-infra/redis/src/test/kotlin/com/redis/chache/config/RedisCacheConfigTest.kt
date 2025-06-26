package com.redis.chache.config

import com.asyncproducer.RedisApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ActiveProfiles


@Import(CacheTestService::class)
@SpringBootTest(classes = [RedisApplication::class])
@ActiveProfiles("test")
class RedisCacheConfigTest (
    @Autowired private val cacheTestService: CacheTestService,
    @Autowired private val redisTemplate: RedisTemplate<String, String>
) {
    @Test
    fun `캐시가 Redis에 저장되는지 확인`() {
        // given
        val key = "123"

        // when
        val first = cacheTestService.getExpensiveValue(key)

        val redisKey = "test::123"
        val cached = redisTemplate.opsForValue().get(redisKey)

        assertThat(cached).isNotNull()
    }
}