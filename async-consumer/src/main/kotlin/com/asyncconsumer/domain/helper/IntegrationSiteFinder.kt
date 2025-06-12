package com.asyncconsumer.domain.helper

import com.rds.comparison.IntegrationSiteCode
import com.rds.comparison.IntegrationSiteRepository
import com.rds.comparison.getByCodeOrThrow
import org.springframework.stereotype.Component

@Component
class IntegrationSiteFinder (
    private val integrationSiteRepository: IntegrationSiteRepository,
){
    fun getByCode(code: IntegrationSiteCode) = integrationSiteRepository.getByCodeOrThrow(code)
}