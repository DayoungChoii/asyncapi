package com.asyncconsumer.comparison.dto

import com.rds.comparison.IntegrationSite
import com.rds.comparison.IntegrationType
import com.rds.product.Product
import jakarta.validation.constraints.NotBlank

data class IntegrationSiteCreationRequest (
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val code: String,
    val baseUrl: String,
    @field:NotBlank
    val integrationType: IntegrationType,
    val apiEndPoint: String,
)

fun IntegrationSiteCreationRequest.toIntegrationSite(): IntegrationSite =
    IntegrationSite(
        name = name,
        code = code,
        baseUrl = baseUrl,
        integrationType = integrationType,
        apiEndPoint = apiEndPoint
        )
data class PriceComparisonRequest (
    val product: Product,
    val integrationSite: IntegrationSite,
)