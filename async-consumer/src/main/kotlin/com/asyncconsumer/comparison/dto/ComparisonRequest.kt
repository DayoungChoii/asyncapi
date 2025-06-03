package com.asyncconsumer.comparison.dto

import com.rds.comparison.IntegrationSite
import com.rds.comparison.IntegrationType
import jakarta.validation.constraints.NotBlank

data class IntegrationSiteCreationRequest (
    @field:NotBlank
    val name: String,
    val baseUrl: String,
    @field:NotBlank
    val integrationType: IntegrationType,
    val apiEndPoint: String,
)

fun IntegrationSiteCreationRequest.toIntegrationSite(): IntegrationSite =
    IntegrationSite(
        name = name,
        baseUrl = baseUrl,
        integrationType = integrationType,
        apiEndPoint = apiEndPoint
        )
