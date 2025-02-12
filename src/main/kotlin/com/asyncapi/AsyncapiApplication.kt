package com.asyncapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncapiApplication

fun main(args: Array<String>) {
	runApplication<AsyncapiApplication>(*args)
}
