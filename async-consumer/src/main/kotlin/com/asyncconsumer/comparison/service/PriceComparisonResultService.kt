package com.asyncconsumer.comparison.service

import com.asyncconsumer.common.exception.comparison.IntegrationSiteNotFoundException
import com.asyncconsumer.common.exception.product.ProductNotFoundException
import com.asyncconsumer.comparison.dto.PriceComparisonResultRequest
import com.rds.comparison.*
import com.rds.product.Product
import com.rds.product.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PriceComparisonResultService(
    private val comparisonRepository: PriceComparisonRepository,
    private val comparisonLogRepository: PriceComparisonLogRepository,
    private val productRepository: ProductRepository,
    private val integrationSiteRepository: IntegrationSiteRepository
) {
    @Transactional
    fun saveResult(request: PriceComparisonResultRequest) {
        val product = findProductById(request.productId)
        val site = findPriceComparisonByCode(request.partnerSiteCode)
        val priceComparison = findComparisonByProductIdAndSiteId(product, site)

        priceComparison?.update(request.price, request.productUrl) ?: savePriceComparison(product, site, request)
        saveComparisonLog(product, site, request)
    }

    private fun savePriceComparison(
        product: Product,
        site: IntegrationSite,
        request: PriceComparisonResultRequest
    ) {
        comparisonRepository.save(
            PriceComparison(
                product = product,
                integrationSite = site,
                price = request.price,
                productUrl = request.productUrl
            )
        )
    }

    private fun findComparisonByProductIdAndSiteId(
        product: Product,
        site: IntegrationSite
    ) = comparisonRepository.findByProductIdAndIntegrationSiteId(product.id, site.id)

    private fun findPriceComparisonByCode(code: IntegrationSiteCode) =
        (integrationSiteRepository.findByCode(code)
            ?: throw IntegrationSiteNotFoundException())

    private fun findProductById(productId: Long) =
        (productRepository.findByIdOrNull(productId)
            ?: throw ProductNotFoundException())

    private fun saveComparisonLog(
        product: Product,
        site: IntegrationSite,
        request: PriceComparisonResultRequest
    ) {
        val logEntry = PriceComparisonLog(
            product = product,
            integrationSite = site,
            price = request.price,
            productUrl = request.productUrl
        )
        comparisonLogRepository.save(logEntry)
    }
}