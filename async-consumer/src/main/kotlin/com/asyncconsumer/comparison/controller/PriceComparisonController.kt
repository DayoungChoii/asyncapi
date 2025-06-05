package com.asyncconsumer.comparison.controller

import com.asyncconsumer.common.StatusResult
import com.asyncconsumer.common.toResponse
import com.asyncconsumer.comparison.constant.PriceComparisonStatus
import com.asyncconsumer.comparison.constant.PriceComparisonStatus.*
import com.asyncconsumer.comparison.service.PriceComparisonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PriceComparisonController (
    private val priceComparisonService: PriceComparisonService
) {

    fun performPriceComparison(productId: Long): ResponseEntity<StatusResult<PriceComparisonStatus>> {
        priceComparisonService.startComparison(productId)
        return StatusResult(SUCCESS).toResponse()
    }

}
