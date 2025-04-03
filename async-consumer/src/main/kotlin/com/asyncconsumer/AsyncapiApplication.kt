package com.asyncproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncConsumerApplication

fun main(args: Array<String>) {
	runApplication<AsyncConsumerApplication>(*args)
}
