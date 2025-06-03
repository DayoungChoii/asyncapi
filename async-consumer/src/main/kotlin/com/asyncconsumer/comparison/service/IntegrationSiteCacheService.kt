package com.asyncconsumer.comparison.service

import com.rds.comparison.IntegrationSite
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class IntegrationSiteCacheService {
    @CachePut(cacheNames = ["integrationSite"], key = "#integrationSite.id")
    fun cacheIntegrationSite(integrationSite: IntegrationSite): IntegrationSite = integrationSite
}