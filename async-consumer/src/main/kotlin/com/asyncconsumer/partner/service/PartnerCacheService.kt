package com.asyncconsumer.partner.service

import com.rds.partner.Partner
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class PartnerCacheService {
    @CachePut(cacheNames = ["partner"], key = "#partner.id")
    fun cachePartner(partner: Partner): Partner = partner
}