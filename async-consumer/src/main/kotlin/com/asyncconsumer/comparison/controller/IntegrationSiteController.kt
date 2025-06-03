package com.asyncconsumer.comparison.controller

import com.asyncconsumer.common.StatusDataResult
import com.asyncconsumer.common.toResponse
import com.asyncconsumer.partner.constant.PartnerCreationStatus
import com.asyncconsumer.partner.dto.PartnerCreationRequest
import com.asyncconsumer.partner.service.PartnerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class IntegrationSiteController (
    private val partnerService: PartnerService
) {

    fun createPartner(request: PartnerCreationRequest): ResponseEntity<StatusDataResult<PartnerCreationStatus, Long>> =
        partnerService.createPartner(request).toResponse()
}
