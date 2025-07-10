package com.asyncconsumer.common

import com.rds.comparison.IntegrationSite
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
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
        block()
    }
}