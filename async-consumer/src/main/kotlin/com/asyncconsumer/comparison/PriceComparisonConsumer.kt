package com.asyncconsumer.comparison

import com.asyncconsumer.common.logger.logger
import com.asyncconsumer.comparison.service.PriceComparisonService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class PriceComparisonConsumer(
    private val priceComparisonService: PriceComparisonService
) {
    private val log = logger()

    @KafkaListener(
        topics = ["price-compare-requests"],
        groupId = "price-compare-consumer-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    suspend fun consume(@Payload request: PriceCompareRequestMessage) {
         priceComparisonService.startComparison(request.productId)

    }
}

data class PriceCompareRequestMessage(val productId: Long)