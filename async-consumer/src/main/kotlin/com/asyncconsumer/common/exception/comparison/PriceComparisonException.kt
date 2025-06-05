package com.asyncconsumer.common.exception.comparison

import com.asyncconsumer.common.exception.CustomException
import com.asyncconsumer.common.exception.CustomExceptionType

class ExternalPriceApiException(
    e: Exception,
    customExceptionType: CustomExceptionType = ExternalPriceApiExceptionType
): CustomException(customExceptionType, e)