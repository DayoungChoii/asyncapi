package com.asyncconsumer.common.exception

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(CustomException::class)
    fun handleException(e: CustomException, request: HttpServletRequest): ResponseEntity<String?> {
        log.error("ERROR[] : ${e.message} \n ${e.stackTraceToString()}")
        return ExceptionResult(e.httpStatusCode, e.message).toResponse()
    }
}