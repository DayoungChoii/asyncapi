package com.asyncproducer.comparison

import java.math.BigDecimal

data class PriceComparisonResultSummary (
    val productId: Long,
    val productName: String,
    val siteId: Long,
    val siteName: String,
    val price: BigDecimal,
    val productUrl: String,
)