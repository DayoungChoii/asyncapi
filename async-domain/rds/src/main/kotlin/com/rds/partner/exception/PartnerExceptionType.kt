package com.rds.partner.exception

import com.asyncsharedkernel.exception.CustomExceptionType
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

object PartnerNotFoundExceptionType: CustomExceptionType {
    override val httpStatusCode: HttpStatusCode = HttpStatus.BAD_REQUEST
    override val message: String = "Partner cannot find"
}