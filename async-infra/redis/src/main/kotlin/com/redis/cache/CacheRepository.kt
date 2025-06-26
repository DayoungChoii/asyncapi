package com.redis.cache

import java.time.Duration

interface CacheRepository {
    suspend fun <T> get(key: String, clazz: Class<T>): T?
    suspend fun <T> set(key: String, value: T, ttl: Duration? = null)
    suspend fun delete(key: String)
}