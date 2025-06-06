package com.asyncconsumer.testsupport

import com.appmattus.kotlinfixture.kotlinFixture
import com.rds.comparison.IntegrationSite
import com.rds.comparison.IntegrationSiteCode

object IntegrationSiteFixtures {
    private val fixture = kotlinFixture()

    fun createFakeSite(code: IntegrationSiteCode) = fixture<IntegrationSite> {
        property(IntegrationSite::code) { code }
    }

    fun createAllFakeSites() = IntegrationSiteCode.values().map { code -> createFakeSite(code) }
}