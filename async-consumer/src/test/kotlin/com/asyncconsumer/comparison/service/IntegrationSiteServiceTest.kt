package com.asyncconsumer.comparison.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.asyncconsumer.AsyncConsumerApplication
import com.asyncconsumer.comparison.dto.IntegrationSiteCreationRequest
import com.asyncconsumer.partner.constant.PartnerCreationStatus
import com.rds.comparison.IntegrationSiteCode.*
import com.rds.comparison.IntegrationSiteRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [AsyncConsumerApplication::class])
class IntegrationSiteServiceTest (
    @Autowired private val integrationSiteService: IntegrationSiteService,
    @Autowired private val integrationSiteRepository: IntegrationSiteRepository
) {
    private val fixture = kotlinFixture()

    @Test
    fun `IntegrationSite 생성 테스트`() {
        //given
        val request: IntegrationSiteCreationRequest = fixture<IntegrationSiteCreationRequest>() {
            property(IntegrationSiteCreationRequest::code) { COUPUNG }
        }

        //when
        val result = integrationSiteService.createIntegrationSite(request)

        //then
        val savedIntegrationSite = integrationSiteRepository.findById(result.data).get()

        Assertions.assertThat(result.status).isEqualTo(PartnerCreationStatus.SUCCESS)
        Assertions.assertThat(result.data).isEqualTo(savedIntegrationSite.id)
    }
}