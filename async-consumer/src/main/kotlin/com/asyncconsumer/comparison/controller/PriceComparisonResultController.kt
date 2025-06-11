package com.asyncconsumer.comparison.controller

import com.asyncconsumer.comparison.dto.PriceComparisonResultRequest
import com.asyncconsumer.comparison.service.PriceComparisonResultService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/price-comparison")
class PriceComparisonResultController(
    private val resultService: PriceComparisonResultService
) {

    @PostMapping("/result")
    fun receiveResult(@RequestBody request: PriceComparisonResultRequest): ResponseEntity<Unit> {
        resultService.saveResult(request)
        return ResponseEntity.ok().build() // TODO 규약에 의한 응답 필요
    }
}