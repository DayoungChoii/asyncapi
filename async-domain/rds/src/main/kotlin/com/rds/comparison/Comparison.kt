package com.rds.comparison

import com.rds.BaseTimeEntity
import com.rds.partner.Partner
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
class Comparison (
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "partner_id")
    val partner: Partner,
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,
    val price: BigDecimal,

): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L
}