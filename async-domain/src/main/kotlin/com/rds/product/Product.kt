package com.rds.product

import com.rds.BaseTimeEntity
import com.rds.partner.Partner
import com.rds.product.ProductStatus.ACTIVE
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GenerationType.IDENTITY
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
    @Enumerated(EnumType.STRING)
    val status: ProductStatus = ACTIVE
}