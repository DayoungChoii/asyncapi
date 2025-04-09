package com.rds.partner

import com.rds.BaseTimeEntity
import com.rds.partner.PartnerStatus.ACTIVE
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id

@Entity
class Partner (
    val name: String,
    val contactEmail: String,
    val code: String,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L
    val status: PartnerStatus = ACTIVE
}