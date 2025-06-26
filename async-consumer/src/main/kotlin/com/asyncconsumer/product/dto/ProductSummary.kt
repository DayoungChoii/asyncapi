package com.asyncconsumer.product.dto

import com.rds.partner.Partner
import com.rds.product.Product
import java.math.BigDecimal

data class ProductSummary (
    val id: Long,
    val name: String,
    val price: BigDecimal,
) {
    companion object {
        fun of(product: Product) =
            ProductSummary(product.id, product.name, product.price)

    }
}