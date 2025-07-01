package com.asyncproducer.common

import org.springframework.http.ResponseEntity

class StatusResult<S> (
    val status: S
)

class DataResult<T> (
    val data: T
)

class StatusDataResult<T, U> (
    val status: T,
    val data: U
)

fun <S> StatusResult<S>.toResponse() = ResponseEntity.ok(this)
fun <S> DataResult<S>.toResponse() = ResponseEntity.ok(this)
fun <T, U> StatusDataResult<T, U>.toResponse() = ResponseEntity.ok(this)