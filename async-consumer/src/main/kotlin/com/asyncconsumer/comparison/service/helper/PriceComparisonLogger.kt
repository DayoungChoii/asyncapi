package com.asyncconsumer.comparison.service.helper

import com.asyncconsumer.comparison.dto.PriceComparisonResultRequest
import com.rds.comparison.IntegrationSite
import com.rds.comparison.PriceComparisonLog
import com.rds.comparison.PriceComparisonLogRepository
import com.rds.product.Product
import org.springframework.stereotype.Component

@Component
class PriceComparisonLogger (
    private val comparisonLogRepository: PriceComparisonLogRepository
) {
    fun log(product: Product, site: IntegrationSite, request: PriceComparisonResultRequest) {
        comparisonLogRepository.save(
            PriceComparisonLog(
                product = product,
                integrationSite = site,
                price = request.price,
                productUrl = request.productUrl
            )
        )
    }
}