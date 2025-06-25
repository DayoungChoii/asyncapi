package com.rds.comparison

import com.rds.comparison.exception.IntegrationSiteNotFoundException

fun IntegrationSiteRepository.getByCodeOrThrow(code: IntegrationSiteCode): IntegrationSite =
    this.findByCode(code) ?: throw IntegrationSiteNotFoundException()