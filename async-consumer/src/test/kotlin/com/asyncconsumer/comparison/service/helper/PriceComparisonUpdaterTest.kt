package com.asyncconsumer.comparison.service.helper

import com.asyncconsumer.comparison.dto.PriceComparisonResultRequest
import com.asyncconsumer.testsupport.IntegrationSiteFixtures
import com.asyncconsumer.testsupport.ProductFixtures
import com.rds.comparison.IntegrationSiteCode
import com.rds.comparison.PriceComparison
import com.rds.comparison.PriceComparisonRepository
import com.rds.product.Product
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal

@ExtendWith(MockKExtension::class)
class PriceComparisonUpdaterTest {

    @MockK
    lateinit var comparisonRepository: PriceComparisonRepository

    lateinit var sut: PriceComparisonUpdater

    val product = ProductFixtures.createProduct()
    val site = IntegrationSiteFixtures.createFakeSite(IntegrationSiteCode.COUPUNG)
    val request = PriceComparisonResultRequest(
        productId = 1L,
        partnerSiteCode = IntegrationSiteCode.COUPUNG,
        price = BigDecimal("9900.00"),
        productUrl = "http://example.com/product"
    )

    @BeforeEach
    fun setUp() {
        sut = PriceComparisonUpdater(comparisonRepository)
    }

    @Test
    fun `기존 PriceComparison이 있으면 update만 호출된다`() {
        // given
        val priceComparison = mockk<PriceComparison>(relaxed = true)
        every {
            comparisonRepository.findByProductIdAndIntegrationSiteId(product.id, site.id)
        } returns priceComparison

        // when
        sut.upsert(product, site, request)

        // then
        verify { priceComparison.update(request.price, request.productUrl) }
        verify(exactly = 0) { comparisonRepository.save(any()) }
    }

    @Test
    fun `기존 PriceComparison이 없으면 save가 호출된다`() {
        // given
        every {
            comparisonRepository.findByProductIdAndIntegrationSiteId(product.id, site.id)
        } returns null
        every { comparisonRepository.save(any()) } returns mockk()

        // when
        sut.upsert(product, site, request)

        // then
        verify {
            comparisonRepository.save(
                match {
                    it.product == product &&
                    it.integrationSite == site &&
                    it.price == request.price &&
                    it.productUrl == request.productUrl
                }
            )
        }
    }
}