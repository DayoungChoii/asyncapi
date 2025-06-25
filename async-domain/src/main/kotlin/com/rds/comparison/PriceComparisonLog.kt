package com.rds.comparison

import com.rds.BaseTimeEntity
import com.rds.product.Product
import jakarta.persistence.*
import jakarta.persistence.FetchType.*
import jakarta.persistence.GenerationType.*
import java.math.BigDecimal

@Entity
class PriceComparisonLog (
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "integration_id")
    val integrationSite: IntegrationSite,
    val price: BigDecimal,
    val productUrl: String,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L
}