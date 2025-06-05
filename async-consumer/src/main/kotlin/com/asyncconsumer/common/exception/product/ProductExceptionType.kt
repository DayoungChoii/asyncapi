package com.asyncconsumer.common.exception.product

import com.asyncconsumer.common.exception.CustomExceptionType
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

object ProductNotFoundExceptionType: CustomExceptionType {
    override val httpStatusCode: HttpStatusCode = HttpStatus.BAD_REQUEST
    override val message: String = "Product cannot find"
}