package com.asyncconsumer.comparison.service.adapter

import com.asyncconsumer.comparison.dto.PriceComparisonRequest

interface IntegrationSiteAdapter {
    suspend fun compareProductPrice(priceComparisonRequest: PriceComparisonRequest)
}