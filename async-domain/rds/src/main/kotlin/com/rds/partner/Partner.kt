package com.rds.partner

import com.rds.BaseTimeEntity
import com.rds.partner.PartnerStatus.ACTIVE
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
class Partner (
    val name: String,
    val contactEmail: String,
    val code: String,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L
    @Enumerated(EnumType.STRING)
    val status: PartnerStatus = ACTIVE
}