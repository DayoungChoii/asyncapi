package com.asyncconsumer.common.exception.comparison

import com.asyncsharedkernel.exception.CustomExceptionType
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

object ExternalPriceApiExceptionType: CustomExceptionType {
    override val httpStatusCode: HttpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR
    override val message: String = "External API fail"
}