package com.rds.product

import com.rds.BaseTimeEntity
import com.rds.partner.Partner
import com.rds.product.ProductStatus.ACTIVE
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class Product (
    val name: String,
    val description: String? = null,
    val price: BigDecimal,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "partner_id", nullable = false)
    val partner: Partner,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L
    val status: ProductStatus = ACTIVE
}