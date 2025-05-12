package com.asyncconsumer.common.exception

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity

class ExceptionResult (
    val code: HttpStatusCode,
    val message: String?
) {
    fun toResponse() = ResponseEntity.status(code).body(message)
}