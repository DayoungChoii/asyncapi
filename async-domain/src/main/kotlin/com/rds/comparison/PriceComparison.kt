package com.rds.comparison

import com.rds.BaseTimeEntity
import com.rds.product.Product
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class PriceComparison (
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "integration_id")
    val integrationSite: IntegrationSite,
    var price: BigDecimal,
    var productUrl: String,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L

    fun update(price: BigDecimal, productUrl: String) {
        this.price = price
        this.productUrl = productUrl
    }
}