package com.asyncconsumer.common

import com.asyncconsumer.common.exception.comparison.ExternalPriceApiException
import com.asyncconsumer.common.exception.comparison.ExternalPriceApiTimeOutException
import com.asyncconsumer.common.exception.comparison.InternalPriceApiTimeOutException
import com.rds.comparison.IntegrationSite
import io.netty.handler.timeout.ReadTimeoutException
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.client.WebClientResponseException
import kotlin.coroutines.CoroutineContext

fun CoroutineScope.alarmLaunch(
    site: IntegrationSite,
    context: CoroutineContext = Dispatchers.Default,
    log: org.slf4j.Logger = LoggerFactory.getLogger("CoroutineExtensions"),
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(context + CoroutineExceptionHandler { _, e ->
        log.error("가격 요청 실패 [site=${site.code}] : ${e.message}", e)
        //TODO: 모니터링 시스템 연동
    }) {
        try {
            withTimeout(60_000) {
                block()
            }
        } catch (e: WebClientResponseException) {
            throw ExternalPriceApiException(e)
        } catch (e: ReadTimeoutException) {
            throw ExternalPriceApiTimeOutException(e)
        } catch (e: TimeoutCancellationException) {
            throw InternalPriceApiTimeOutException(e)
        } catch (e: Exception) {
            throw e
        }
    }
}