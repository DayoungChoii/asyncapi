package com.asyncconsumer.common.exception.comparison

import com.asyncsharedkernel.exception.CustomException
import com.asyncsharedkernel.exception.CustomExceptionType

class ExternalPriceApiException(
    e: Exception,
    customExceptionType: CustomExceptionType = ExternalPriceApiExceptionType
): CustomException(customExceptionType, e)

class ExternalPriceApiTimeOutException(
    e: Exception,
    customExceptionType: CustomExceptionType = ExternalPriceApiTimeOutExceptionType
): CustomException(customExceptionType, e)

class InternalPriceApiTimeOutException(
    e: Exception,
    customExceptionType: CustomExceptionType = ExternalPriceApiTimeOutExceptionType
): CustomException(customExceptionType, e)