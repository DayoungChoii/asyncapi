package com.asyncconsumer.comparison.service

import com.asyncconsumer.common.StatusDataResult
import com.asyncconsumer.comparison.dto.IntegrationSiteCreationRequest
import com.asyncconsumer.comparison.dto.toIntegrationSite
import com.asyncconsumer.partner.constant.PartnerCreationStatus
import com.asyncconsumer.partner.constant.PartnerCreationStatus.*
import com.rds.comparison.IntegrationSiteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class IntegrationSiteService (
    private val integrationSiteRepository: IntegrationSiteRepository,
) {
    @Transactional
    fun createIntegrationSite(request: IntegrationSiteCreationRequest): StatusDataResult<PartnerCreationStatus, Long> {
        val integrationSite = integrationSiteRepository.save(request.toIntegrationSite())
        return StatusDataResult(SUCCESS, integrationSite.id)
    }
}
