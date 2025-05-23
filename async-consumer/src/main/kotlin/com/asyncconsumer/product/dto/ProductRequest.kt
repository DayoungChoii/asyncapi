package com.asyncconsumer.product.dto

import com.rds.partner.Partner
import com.rds.product.Product
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class ProductCreationRequest (
    @field:NotBlank
    val name: String,
    val description: String,
    @field:NotBlank
    val price: BigDecimal,
    val partnerId: Long,
) {
    fun toProduct(partner: Partner) =
        Product(
            name = name,
            description = description,
            price = price,
            partner = partner
        )
}
