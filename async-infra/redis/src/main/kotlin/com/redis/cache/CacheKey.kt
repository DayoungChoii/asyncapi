package com.redis.cache

object CacheKey {
    fun partner(id: Long): String = "partner:$id"
    fun product(id: Long): String = "product:$id"
}