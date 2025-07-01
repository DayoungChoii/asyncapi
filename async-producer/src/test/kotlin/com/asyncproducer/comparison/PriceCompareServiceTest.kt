package com.asyncproducer.comparison

import com.appmattus.kotlinfixture.kotlinFixture
import com.redis.cache.CacheKey
import com.redis.cache.CacheRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PriceCompareServiceTest {

    @MockK
    lateinit var priceCompareProducer: PriceCompareProducer

    @MockK
    lateinit var cacheRepository: CacheRepository

    lateinit var priceCompareService: PriceCompareService

    @BeforeEach
    fun setUp() {
        priceCompareService = PriceCompareService(priceCompareProducer, cacheRepository)
    }

    private val fixture = kotlinFixture()

    @Test
    fun `캐시에 데이터가 있으면 CacheHit 리턴`() = runTest {
        val productId = 1L
        val cachedData = fixture<PriceComparisonResultSummary>()

        coEvery {
            cacheRepository.get(CacheKey.priceComparison(productId), PriceComparisonResultSummary::class.java)
        } returns cachedData

        val result = priceCompareService.requestPriceComparison(productId)

        assertTrue(result is PriceCompareResponse.CacheHit)
        assertEquals(cachedData, (result as PriceCompareResponse.CacheHit).data)
        coVerify(exactly = 0) { priceCompareProducer.sendCompareRequest(any()) }
    }

    @Test
    fun `캐시에 데이터 없으면 CacheMiss 리턴하고 메시지 발송`() = runTest {
        val productId = 2L

        coEvery {
            cacheRepository.get(CacheKey.priceComparison(productId), PriceComparisonResultSummary::class.java)
        } returns null

        coJustRun { priceCompareProducer.sendCompareRequest(productId) }

        val result = priceCompareService.requestPriceComparison(productId)

        assertTrue(result is PriceCompareResponse.CacheMiss)
        coVerify { priceCompareProducer.sendCompareRequest(productId) }
    }
}

