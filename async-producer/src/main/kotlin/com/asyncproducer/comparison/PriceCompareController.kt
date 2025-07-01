package com.asyncproducer.comparison

import com.asyncproducer.common.DataResult
import com.asyncproducer.common.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/compare")
class PriceCompareController (
    private val priceCompareService: PriceCompareService
) {
    @PostMapping("/{productId}")
    suspend fun requestCompare(@PathVariable productId: Long): ResponseEntity<DataResult<PriceCompareResponse>> =
        DataResult(priceCompareService.requestPriceComparison(productId)).toResponse()
}