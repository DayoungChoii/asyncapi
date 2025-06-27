package com.asyncconsumer.comparison.dto

import com.rds.comparison.IntegrationSite
import com.rds.product.Product
import java.math.BigDecimal

data class PriceComparisonResultSummary (
    val productId: Long,
    val productName: String,
    val siteId: Long,
    val siteName: String,
    val price: BigDecimal,
    val productUrl: String,
) {
    companion object {
        fun of(product: Product, site: IntegrationSite, price: BigDecimal, productUrl: String) =
            PriceComparisonResultSummary(product.id, product.name, site.id, site.name, price, productUrl)

    }
}