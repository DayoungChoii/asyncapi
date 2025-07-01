package com.redis.cache

object CacheKey {
    private const val PARTNER_PREFIX = "partner"
    private const val PRODUCT_PREFIX = "product"
    private const val PRICE_COMPARISON_PREFIX = "product_comparison"

    fun partner(id: Long): String = "$PARTNER_PREFIX:$id"
    fun product(id: Long): String = "$PRODUCT_PREFIX:$id"
    fun priceComparison(productId: Long): String = "$PRICE_COMPARISON_PREFIX:$productId"
}