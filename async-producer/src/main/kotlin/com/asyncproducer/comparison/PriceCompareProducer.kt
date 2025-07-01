package com.asyncproducer.comparison

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class PriceCompareProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    suspend fun sendCompareRequest(productId: Long) {
        val message = """{"productId": "$productId"}"""
        kafkaTemplate.send("price-compare-requests", productId.toString(), message)
    }
}