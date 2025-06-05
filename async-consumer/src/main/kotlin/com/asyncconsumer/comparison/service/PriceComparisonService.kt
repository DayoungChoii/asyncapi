package com.asyncconsumer.comparison.service

import com.asyncconsumer.common.exception.comparison.ExternalPriceApiException
import com.asyncconsumer.common.exception.product.ProductNotFoundException
import com.asyncconsumer.comparison.dto.PriceComparisonRequest
import com.asyncconsumer.comparison.service.adapter.IntegrationSiteAdapterFactory
import com.rds.comparison.IntegrationSiteRepository
import com.rds.product.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PriceComparisonService (
    private val applicationScope: CoroutineScope,
    private val integrationSiteRepository: IntegrationSiteRepository,
    private val productRepository: ProductRepository,
    private val integrationSiteAdapterFactory: IntegrationSiteAdapterFactory,
) {

    companion object {
        private val log = LoggerFactory.getLogger(PriceComparisonService::class.java)
    }
    fun startComparison(productId: Long) {
        val product = productRepository.findByIdOrNull(productId) ?: throw ProductNotFoundException()
        val integrationSites = integrationSiteRepository.findAll()

        integrationSites.forEach { site ->
            applicationScope.launch {
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
