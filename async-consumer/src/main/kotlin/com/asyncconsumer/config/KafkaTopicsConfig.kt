package com.asyncconsumer.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaTopicsConfig {
    @Bean
    fun priceCompareRequests() = NewTopic("price-compare-requests", 3, 1)

    @Bean
    fun priceCompareRequestsDlt() = NewTopic("price-compare-requests.DLT", 3, 1)
}
