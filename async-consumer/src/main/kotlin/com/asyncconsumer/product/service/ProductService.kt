package com.asyncconsumer.product.service

import com.asyncconsumer.common.StatusDataResult
import com.asyncconsumer.common.exception.partner.PartnerNotFoundException
import com.asyncconsumer.product.constant.ProductCreationStatus
import com.asyncconsumer.product.constant.ProductCreationStatus.*
import com.asyncconsumer.product.dto.ProductCreationRequest
import com.rds.partner.PartnerRepository
import com.rds.product.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductService (
    private val productCacheService: ProductCacheService,
    private val productRepository: ProductRepository,
    private val partnerRepository: PartnerRepository,
) {
    @Transactional
    fun createProduct(request: ProductCreationRequest): StatusDataResult<ProductCreationStatus, Long> {
        val partner = partnerRepository.findByIdOrNull(request.partnerId) ?: throw PartnerNotFoundException()
        val product = productRepository.save(request.toProduct(partner))
        productCacheService.cachePartner(product)
        return StatusDataResult(SUCCESS, product.id)
    }
}
