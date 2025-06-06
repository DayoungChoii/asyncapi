package com.asyncconsumer.comparison.service.adapter

import com.rds.comparison.IntegrationSiteCode
import org.springframework.stereotype.Component

@Component
class IntegrationSiteAdapterFactory(
    private val adapters: Map<String, IntegrationSiteAdapter>
) {
    fun getAdapter(code: IntegrationSiteCode): IntegrationSiteAdapter =
        adapters[code.name] ?: throw IllegalArgumentException("지원하지 않는 사이트입니다: $code")
}