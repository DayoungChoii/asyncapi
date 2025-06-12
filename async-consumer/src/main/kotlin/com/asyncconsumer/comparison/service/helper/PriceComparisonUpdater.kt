package com.asyncconsumer.comparison.service.helper

import com.asyncconsumer.comparison.dto.PriceComparisonResultRequest
import com.rds.comparison.IntegrationSite
import com.rds.comparison.PriceComparison
import com.rds.comparison.PriceComparisonRepository
import com.rds.product.Product
import org.springframework.stereotype.Component

@Component
class PriceComparisonUpdater (
    private val comparisonRepository: PriceComparisonRepository
) {
    fun upsert(product: Product, site: IntegrationSite, request: PriceComparisonResultRequest) {
        val priceComparison = comparisonRepository.findByProductIdAndIntegrationSiteId(product.id, site.id)
        priceComparison?.update(request.price, request.productUrl) ?: savePriceComparison(product, site, request)
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
}