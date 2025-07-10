package com.asyncconsumer.comparison.service

import com.asyncconsumer.common.alarmLaunch
import com.asyncconsumer.comparison.dto.PriceComparisonRequest
import com.asyncconsumer.comparison.service.adapter.IntegrationSiteAdapterFactory
import com.asyncconsumer.domain.helper.ProductFinder
import com.rds.comparison.IntegrationSiteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.supervisorScope
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PriceComparisonService (
    private val integrationSiteRepository: IntegrationSiteRepository,
    private val productFinder: ProductFinder,
    private val integrationSiteAdapterFactory: IntegrationSiteAdapterFactory,
) {

    companion object {
        private val log = LoggerFactory.getLogger(PriceComparisonService::class.java)
    }
    suspend fun startComparison(productId: Long) = supervisorScope {
        val product = productFinder.getProductById(productId)
        val integrationSites = integrationSiteRepository.findAll()

        integrationSites.forEach { site ->
            alarmLaunch(site, Dispatchers.IO, log) {
                val adapter = integrationSiteAdapterFactory.getAdapter(site.code)
                val comparisonRequest = PriceComparisonRequest(product = product, integrationSite = site)
                adapter.compareProductPrice(comparisonRequest)

            }
        }
    }
}

