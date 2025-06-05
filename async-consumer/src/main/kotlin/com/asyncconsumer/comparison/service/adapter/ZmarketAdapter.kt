package com.asyncconsumer.comparison.service.adapter

import com.asyncconsumer.common.exception.comparison.ExternalPriceApiException
import com.asyncconsumer.comparison.dto.PriceComparisonRequest
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

@Component("ZMARKET")
class ZmarketAdapter: IntegrationSiteAdapter{
    override suspend fun compareProductPrice(priceComparisonRequest: PriceComparisonRequest) {
        val product = priceComparisonRequest.product
        val integrationSite = priceComparisonRequest.integrationSite
        try {
            WebClient.builder()
                .baseUrl(integrationSite.baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .post()
                .uri(integrationSite.apiEndPoint, product.id) //협력사용 productId 변환 필요
                .retrieve()
                .toBodilessEntity()
                .awaitSingle()

        } catch (e: WebClientResponseException) {
            throw ExternalPriceApiException(e)
        }
    }

}