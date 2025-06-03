package com.rds.comparison

import com.rds.BaseTimeEntity
import com.rds.comparison.IntegrationSiteStatus.*
import jakarta.persistence.*
import jakarta.persistence.EnumType.*
import jakarta.persistence.GenerationType.*

@Entity
class IntegrationSite (
    val name: String,
    val code: String,
    val baseUrl: String,
    @Enumerated(STRING)
    val integrationType: IntegrationType,
    val apiEndPoint: String,
    @Enumerated(STRING)
    val status: IntegrationSiteStatus = ACTIVE,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L
}