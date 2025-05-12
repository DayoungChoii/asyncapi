package com.asyncconsumer.common.exception

open class CustomException (
    val type: CustomExceptionType,
    val e: Throwable?
): RuntimeException(type.message, e)