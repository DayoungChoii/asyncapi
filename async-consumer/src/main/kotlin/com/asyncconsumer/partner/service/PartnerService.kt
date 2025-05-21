package com.asyncconsumer.partner.service

import com.asyncconsumer.common.StatusDataResult
import com.asyncconsumer.partner.constant.PartnerCreationStatus
import com.asyncconsumer.partner.constant.PartnerCreationStatus.*
import com.asyncconsumer.partner.dto.PartnerCreationRequest
import com.rds.partner.PartnerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PartnerService (
    private val partnerCacheService: PartnerCacheService,
    private val partnerRepository: PartnerRepository,
) {
    @Transactional
    fun createPartner(request: PartnerCreationRequest): StatusDataResult<PartnerCreationStatus, Long> {
        val partner = partnerRepository.save(request.toPartner())
        partnerCacheService.cachePartner(partner)
        return StatusDataResult(SUCCESS, partner.id)
    }
}
