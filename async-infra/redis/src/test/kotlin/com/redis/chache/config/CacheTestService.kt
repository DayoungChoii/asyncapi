package com.redis.chache.config

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CacheTestService {
    @Cacheable(cacheNames = ["test"], key = "#key")
    fun getExpensiveValue(key: String): String {
        return "value-for-$key-${System.currentTimeMillis()}"
    }
}