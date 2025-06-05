package com.asyncconsumer.comparison.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.asyncconsumer.common.exception.comparison.ExternalPriceApiException
import com.asyncconsumer.comparison.service.adapter.IntegrationSiteAdapter
import com.asyncconsumer.comparison.service.adapter.IntegrationSiteAdapterFactory
import com.rds.comparison.IntegrationSite
import com.rds.comparison.IntegrationSiteRepository
import com.rds.product.Product
import com.rds.product.ProductRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import java.lang.RuntimeException

@OptIn(ExperimentalCoroutinesApi::class)
class PriceComparisonServiceTest {

    val fixture = kotlinFixture()

    private val productRepository = mockk<ProductRepository>()
    private val siteRepository = mockk<IntegrationSiteRepository>()
    private val adapterFactory = mockk<IntegrationSiteAdapterFactory>()
    private val adapter = mockk<IntegrationSiteAdapter>()

    @Test
    fun `어댑터에서 예외가 발생해도 터지지 않고 경고 로그를 남긴다`() = runTest {
        // given
        val fakeProduct = getFakeProduct()
        val fakeSite = getFakeSite()

        every { productRepository.findByIdOrNull(1L) } returns fakeProduct
        every { siteRepository.findAll() } returns listOf(fakeSite)
        every { adapterFactory.getAdapter("COUPUNG") } returns adapter
        coEvery { adapter.compareProductPrice(any()) } throws ExternalPriceApiException(RuntimeException("외부 api 호출 실패"))

        //system under test
        val sut = PriceComparisonService(
            applicationScope = this,
            integrationSiteRepository = siteRepository,
            productRepository = productRepository,
            integrationSiteAdapterFactory = adapterFactory,
        )

        // when
        sut.startComparison(1L)
        advanceUntilIdle() // 코루틴 끝날 때까지 기다리기

        // then
        coVerify(exactly = 1) { adapter.compareProductPrice(any()) }
    }
    private fun getFakeSite() = fixture<IntegrationSite> {
        property(IntegrationSite::code) { "COUPUNG" }
        property(IntegrationSite::baseUrl) { "https://api.kakao.com" }
        property(IntegrationSite::apiEndPoint) { "/search" }
    }

    private fun getFakeProduct() = fixture<Product> {
        property(Product::name) { "테스트 상품" }
    }
}
