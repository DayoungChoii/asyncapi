package com.asyncconsumer.product.controller

import com.asyncconsumer.common.StatusDataResult
import com.asyncconsumer.common.toResponse
import com.asyncconsumer.partner.constant.PartnerCreationStatus
import com.asyncconsumer.partner.dto.PartnerCreationRequest
import com.asyncconsumer.partner.service.PartnerService
import com.asyncconsumer.product.constant.ProductCreationStatus
import com.asyncconsumer.product.dto.ProductCreationRequest
import com.asyncconsumer.product.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController (
    private val productService: ProductService
) {

    fun createPartner(request: ProductCreationRequest): ResponseEntity<StatusDataResult<ProductCreationStatus, Long>> =
        productService.createProduct(request).toResponse()
}
