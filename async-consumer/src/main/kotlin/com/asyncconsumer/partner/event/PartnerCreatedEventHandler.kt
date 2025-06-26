package com.asyncconsumer.partner.event

import com.asyncconsumer.common.logger.logger
import com.asyncconsumer.partner.dto.PartnerSummary
import com.redis.cache.CacheKey
import com.redis.cache.CacheRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class PartnerCreatedEventHandler(
    private val cacheRepository: CacheRepository,
) {
    private val log = logger()

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: PartnerCreatedEvent) {
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                val partner = event.partner
                val partnerSummary = PartnerSummary.of(partner) // 변환 함수 별도 정의

                cacheRepository.set(
                    CacheKey.partner(partnerSummary.id),
                    partnerSummary
                )
            } catch (e: Exception) {
                log.error("파트너 캐시 저장 실패: ${e.message}")
                // TODO: 모니터링 시스템에 알리도록해야
            }
        }
    }
}