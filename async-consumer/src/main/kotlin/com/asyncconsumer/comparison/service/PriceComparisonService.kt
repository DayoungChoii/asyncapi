package com.asyncconsumer.comparison.service

import com.asyncconsumer.common.exception.comparison.ExternalPriceApiException
import com.asyncconsumer.comparison.dto.PriceComparisonRequest
import com.asyncconsumer.comparison.service.adapter.IntegrationSiteAdapterFactory
import com.asyncconsumer.domain.helper.ProductFinder
import com.rds.comparison.IntegrationSiteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
            launch(Dispatchers.IO) {
                try {
                    val adapter = integrationSiteAdapterFactory.getAdapter(site.code)
                    val comparisonRequest = PriceComparisonRequest(product = product, integrationSite = site)
                    adapter.compareProductPrice(comparisonRequest)
                } catch (e: ExternalPriceApiException) {
                    log.warn("가격 요청 실패 [site=${site.code}] : ${e.message}", e)
                } catch (e: Exception) {
                    log.error("알 수 없는 오류 발생 [site=${site.code}]", e)
                }
            }
        }
    }
}

