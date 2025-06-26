package com.asyncconsumer.partner.dto

import com.rds.partner.Partner

data class PartnerSummary (
    val id: Long,
    val name: String,
) {
    companion object {
        fun of(partner: Partner) =
            PartnerSummary(partner.id, partner.name)

    }
}