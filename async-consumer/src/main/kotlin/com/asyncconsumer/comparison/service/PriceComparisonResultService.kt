package com.asyncconsumer.comparison.service

import com.asyncconsumer.comparison.dto.PriceComparisonResultRequest
import com.asyncconsumer.comparison.service.helper.PriceComparisonLogger
import com.asyncconsumer.comparison.service.helper.PriceComparisonUpdater
import com.asyncconsumer.domain.helper.IntegrationSiteFinder
import com.asyncconsumer.domain.helper.ProductFinder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PriceComparisonResultService(
    private val productFinder: ProductFinder,
    private val integrationSiteFinder: IntegrationSiteFinder,
    private val priceComparisonUpdater: PriceComparisonUpdater,
    private val priceComparisonLogger: PriceComparisonLogger,
) {
    @Transactional
    fun saveResult(request: PriceComparisonResultRequest) {
        val product = productFinder.getProductById(request.productId)
        val site = integrationSiteFinder.getByCode(request.partnerSiteCode)
        priceComparisonUpdater.upsert(product, site, request)
        priceComparisonLogger.log(product, site, request)
    }
}