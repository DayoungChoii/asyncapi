package com.asyncconsumer.partner.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.asyncconsumer.AsyncConsumerApplication
import com.asyncconsumer.partner.constant.PartnerCreationStatus.*
import com.asyncconsumer.partner.dto.PartnerCreationRequest
import com.rds.partner.PartnerRepository
import com.rds.partner.PartnerStatus.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [AsyncConsumerApplication::class])
class PartnerServiceTest (
    @Autowired private val partnerService: PartnerService,
    @Autowired private val partnerRepository: PartnerRepository,
) {


    private val fixture = kotlinFixture()

    @Test
    fun `Partner 생성 테스트`() {
         //given
        val request: PartnerCreationRequest = fixture()

        //when
        val result = partnerService.createPartner(request)

        //then
        val savedPartner = partnerRepository.findById(result.data).get()

        assertThat(result.status).isEqualTo(SUCCESS)
        assertThat(result.data).isEqualTo(savedPartner.id)
    }
}