package com.asyncconsumer.partner.dto

import com.rds.partner.Partner
import jakarta.validation.constraints.NotBlank

data class PartnerCreationRequest (
    @field:NotBlank
    private val name: String,
    @field:NotBlank
    private val code: String,
    @field:NotBlank
    private val contractEmail: String,
) {
    fun toPartner() =
        Partner(
            name = name,
            contactEmail = contractEmail,
            code = code
        )
}
