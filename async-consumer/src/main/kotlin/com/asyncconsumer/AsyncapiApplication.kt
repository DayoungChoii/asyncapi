package com.asyncproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@PropertySource("classpath:/secure-aws.properties")
@SpringBootApplication
class AsyncConsumerApplication

fun main(args: Array<String>) {
	runApplication<AsyncConsumerApplication>(*args)
}
