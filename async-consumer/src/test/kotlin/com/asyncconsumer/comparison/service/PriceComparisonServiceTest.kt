package com.asyncconsumer.comparison.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.asyncconsumer.common.exception.comparison.ExternalPriceApiException
import com.asyncconsumer.comparison.service.adapter.IntegrationSiteAdapter
import com.asyncconsumer.comparison.service.adapter.IntegrationSiteAdapterFactory
import com.asyncconsumer.domain.helper.ProductFinder
import com.asyncconsumer.testsupport.AdapterStubs
import com.asyncconsumer.testsupport.IntegrationSiteFixtures
import com.asyncconsumer.testsupport.ProductFixtures
import com.rds.comparison.IntegrationSite
import com.rds.comparison.IntegrationSiteCode
import com.rds.comparison.IntegrationSiteCode.*
import com.rds.comparison.IntegrationSiteRepository
import com.rds.product.Product
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

@OptIn(ExperimentalCoroutinesApi::class)
class PriceComparisonServiceTest {

    private val productFinder = mockk<ProductFinder>()
    private val siteRepository = mockk<IntegrationSiteRepository>()
    private val adapterFactory = mockk<IntegrationSiteAdapterFactory>()
    private val adapter = mockk<IntegrationSiteAdapter>()

    @Test
    fun `어댑터에서 예외가 발생해도 터지지 않고 경고 로그를 남긴다`() = runTest {
        // given
        val fakeProduct = ProductFixtures.createProduct()
        val fakeSite = IntegrationSiteFixtures.createFakeSite(COUPUNG)

        stubRepositories(fakeProduct, listOf(fakeSite))
        every { adapterFactory.getAdapter(COUPUNG) } returns adapter
        coEvery { adapter.compareProductPrice(any()) } throws ExternalPriceApiException(RuntimeException("외부 api 호출 실패"))

        //system under test
        val sut = createService()

        // when
        sut.startComparison(1L)
        advanceUntilIdle() // 코루틴 끝날 때까지 기다리기

        // then
        coVerify(exactly = 1) { adapter.compareProductPrice(any()) }
    }
    @Test
    fun `여러 개의 사이트들에서 가격을 가져오는 도중 하나의 사이트에서 예외가 발생해도 나머지 사이트는 정상작동한다`() = runTest {
        // given
        val fakeProduct = ProductFixtures.createProduct()
        val fakeSites = IntegrationSiteFixtures.createAllFakeSites()

        stubRepositories(fakeProduct, fakeSites)

        val adapterMap = AdapterStubs.withFirstAdapterFailing(fakeSites, fakeProduct)
        stubAdapterFactory(adapterMap)

        val sut = createService()

        // when
        sut.startComparison(1L)
        advanceUntilIdle()

        // then
        verifyEachAdapterWasCalled(adapterMap)
    }

    private fun verifyEachAdapterWasCalled(adapterMap: Map<IntegrationSiteCode, IntegrationSiteAdapter>) {
        adapterMap.forEach { (code, adapter) ->
            coVerify(exactly = 1) {
                adapter.compareProductPrice(match { it.integrationSite.code == code })
            }
        }
    }

    private fun createService() = PriceComparisonService(
        integrationSiteRepository = siteRepository,
        productFinder = productFinder,
        integrationSiteAdapterFactory = adapterFactory,
    )

    private fun stubAdapterFactory(adapterMap: Map<IntegrationSiteCode, IntegrationSiteAdapter>) {
        values().forEach { code ->
            every { adapterFactory.getAdapter(code) } returns adapterMap.getValue(code)
        }
    }

    private fun stubRepositories(
        fakeProduct: Product,
        fakeSites: List<IntegrationSite>
    ) {
        every { productFinder.getProductById(1L) } returns fakeProduct
        every { siteRepository.findAll() } returns fakeSites
    }

}
