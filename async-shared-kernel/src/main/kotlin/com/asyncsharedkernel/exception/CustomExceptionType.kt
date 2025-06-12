package com.asyncsharedkernel.exception

import org.springframework.http.HttpStatusCode

interface CustomExceptionType {
    val httpStatusCode: HttpStatusCode
    val message: String
}