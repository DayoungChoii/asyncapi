package com.asyncconsumer.partner.service

import com.asyncconsumer.partner.constant.PartnerCreationStatus
import com.asyncconsumer.partner.dto.PartnerCreationRequest
import com.rds.partner.PartnerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PartnerService (
    private val partnerRepository: PartnerRepository,
) {
    @Transactional
    fun createPartner(request: PartnerCreationRequest): PartnerCreationStatus {
        partnerRepository.save(request.toPartner())
        return PartnerCreationStatus.SUCCESS
    }
}
