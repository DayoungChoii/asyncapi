package com.asyncproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.asyncproducer","com.redis"])
class AsyncProducerApplication

fun main(args: Array<String>) {
	runApplication<AsyncProducerApplication>(*args)
}
