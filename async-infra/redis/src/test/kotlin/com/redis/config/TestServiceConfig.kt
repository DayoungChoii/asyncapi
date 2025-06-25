package com.redis.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestServiceConfig {
    @Bean
    fun cacheTestService(): CacheTestService {
        return CacheTestService()
    }
}