package com.rds.comparison.exception

import com.asyncsharedkernel.exception.CustomExceptionType
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatusCode

object IntegrationSiteNotFoundExceptionType: CustomExceptionType {
    override val httpStatusCode: HttpStatusCode = BAD_REQUEST
    override val message: String = "Integration Site doesn't exist"
}