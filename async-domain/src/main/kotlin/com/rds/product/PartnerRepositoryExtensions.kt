package com.rds.product

import com.rds.product.exception.ProductNotFoundException
import org.springframework.data.repository.findByIdOrNull

fun ProductRepository.getByIdOrThrow(id: Long) =
    this.findByIdOrNull(id) ?: throw ProductNotFoundException()