package com.asyncproducer.comparison

import com.redis.cache.CacheKey
import com.redis.cache.CacheRepository
import org.springframework.stereotype.Service

@Service
class PriceCompareService (
    private val priceCompareProducer: PriceCompareProducer,
    private val cacheRepository: CacheRepository,
) {
    suspend fun requestPriceComparison(productId: Long): PriceCompareResponse {
        val cached = cacheRepository.get(CacheKey.priceComparison(productId), PriceComparisonResultSummary::class.java)

        return cached?.let {
            PriceCompareResponse.CacheHit(it)
        } ?: run {
            priceCompareProducer.sendCompareRequest(productId)
            PriceCompareResponse.CacheMiss
        }
    }
}

sealed class PriceCompareResponse {
    data class CacheHit(val data: PriceComparisonResultSummary) : PriceCompareResponse()
    object CacheMiss : PriceCompareResponse()
}