package com.asyncconsumer.common.exception.comparison

import com.asyncconsumer.common.exception.CustomExceptionType
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatusCode

object IntegrationSiteNotFoundExceptionType: CustomExceptionType {
    override val httpStatusCode: HttpStatusCode = BAD_REQUEST
    override val message: String = "Integration Site doesn't exist"
}