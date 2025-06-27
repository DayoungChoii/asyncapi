package com.asyncconsumer.comparison.event

import com.rds.comparison.IntegrationSite
import com.rds.product.Product
import java.math.BigDecimal

class PriceComparedResultEvent(
    val product: Product,
    val site: IntegrationSite,
    val price: BigDecimal,
    val productUrl: String
)