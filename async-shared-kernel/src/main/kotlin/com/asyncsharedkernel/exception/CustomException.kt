package com.asyncsharedkernel.exception

import org.springframework.http.HttpStatusCode

open class CustomException (
    val type: CustomExceptionType,
    val e: Throwable? = null
): RuntimeException(type.message, e) {
    val httpStatusCode: HttpStatusCode get() = type.httpStatusCode
    override val message: String get() = type.message
}