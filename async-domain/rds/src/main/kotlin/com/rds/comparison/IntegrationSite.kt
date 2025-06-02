package com.rds.comparison

import com.rds.BaseTimeEntity
import jakarta.persistence.*
import jakarta.persistence.EnumType.*

@Entity
class IntegrationSite (
    val name: String,
    val baseUrl: String,
    @Enumerated(STRING)
    val integrationType: IntegrationType,
    val apiEndPoint: String,
    @Enumerated(STRING)
    val status: IntegrationSiteStatus,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}