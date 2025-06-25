package com.asyncconsumer.product.service

import com.rds.product.Product
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class ProductCacheService {
    @CachePut(cacheNames = ["product"], key = "#product.id")
    fun cachePartner(product: Product): Product = product
}