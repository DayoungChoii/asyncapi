package com.asyncconsumer.partner.service

import com.asyncconsumer.common.StatusDataResult
import com.asyncconsumer.partner.constant.PartnerCreationStatus
import com.asyncconsumer.partner.constant.PartnerCreationStatus.*
import com.asyncconsumer.partner.dto.PartnerCreationRequest
import com.asyncconsumer.partner.event.PartnerCreatedEvent
import com.rds.partner.PartnerRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PartnerService (
    private val partnerRepository: PartnerRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {
    @Transactional
    fun createPartner(request: PartnerCreationRequest): StatusDataResult<PartnerCreationStatus, Long> {
        val partner = partnerRepository.save(request.toPartner())

        applicationEventPublisher.publishEvent(PartnerCreatedEvent(partner))
        return StatusDataResult(SUCCESS, partner.id)
    }
}
