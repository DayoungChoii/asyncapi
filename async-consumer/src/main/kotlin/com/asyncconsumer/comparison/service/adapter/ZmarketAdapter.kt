package com.asyncconsumer.comparison.service.adapter

import com.asyncconsumer.comparison.dto.PriceComparisonRequest
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Component("ZMARKET")
class ZmarketAdapter: IntegrationSiteAdapter{
    override suspend fun compareProductPrice(priceComparisonRequest: PriceComparisonRequest) {
        val product = priceComparisonRequest.product
        val integrationSite = priceComparisonRequest.integrationSite
        WebClient.builder()
            .baseUrl(integrationSite.baseUrl)
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create().responseTimeout(Duration.ofSeconds(50))
                )
            )
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
            .post()
            .uri(integrationSite.apiEndPoint, product.id) //협력사용 productId 변환 필요
            .retrieve()
            .toBodilessEntity()
            .awaitSingle()
    }

}