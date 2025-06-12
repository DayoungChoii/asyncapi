package com.asyncconsumer.domain.helper

import com.rds.product.ProductRepository
import com.rds.product.getByIdOrThrow
import org.springframework.stereotype.Component

@Component
class ProductFinder (
    private val productRepository: ProductRepository,
){
    fun getProductById(id: Long) = productRepository.getByIdOrThrow(id)
}