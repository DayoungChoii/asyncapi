package com.asyncconsumer.comparison.dto

import com.rds.comparison.IntegrationSite
import com.rds.comparison.IntegrationSiteCode
import com.rds.comparison.IntegrationType
import com.rds.product.Product
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class IntegrationSiteCreationRequest (
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val code: IntegrationSiteCode,
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

data class PriceComparisonResultRequest(
    val productId: Long,
    val partnerSiteCode: IntegrationSiteCode,
    val price: BigDecimal,
    val productUrl: String
)