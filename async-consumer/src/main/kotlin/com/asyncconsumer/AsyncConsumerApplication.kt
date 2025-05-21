package com.asyncconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableCaching
@PropertySource("classpath:/secure-aws.properties")
@EnableJpaRepositories(basePackages = ["com.rds"])
@EntityScan(basePackages = ["com.rds"])
@SpringBootApplication(scanBasePackages = ["com.asyncconsumer", "com.redis"])
class AsyncConsumerApplication

fun main(args: Array<String>) {
	runApplication<AsyncConsumerApplication>(*args)
}
