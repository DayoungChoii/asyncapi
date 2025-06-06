package com.asyncconsumer.testsupport

import com.asyncconsumer.common.exception.comparison.ExternalPriceApiException
import com.asyncconsumer.comparison.dto.PriceComparisonRequest
import com.asyncconsumer.comparison.service.adapter.IntegrationSiteAdapter
import com.rds.comparison.IntegrationSite
import com.rds.comparison.IntegrationSiteCode
import com.rds.product.Product
import io.mockk.coEvery
import io.mockk.mockk
import java.lang.RuntimeException

object AdapterStubs {
    fun withFirstAdapterFailing(
        sites: List<IntegrationSite>,
        product: Product
    ): Map<IntegrationSiteCode, IntegrationSiteAdapter> = sites.associate { site ->
        site.code to mockk<IntegrationSiteAdapter>().also { adapter ->
            val request = PriceComparisonRequest(product, site)
            if (site.code == sites.first().code) {
                coEvery { adapter.compareProductPrice(request) } throws ExternalPriceApiException(RuntimeException("외부 api 호출 실패"))
            } else {
                coEvery { adapter.compareProductPrice(request) } returns Unit
            }
        }
    }
}