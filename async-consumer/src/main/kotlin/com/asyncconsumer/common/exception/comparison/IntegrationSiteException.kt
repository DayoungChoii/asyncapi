package com.asyncconsumer.common.exception.comparison

import com.asyncconsumer.common.exception.CustomException
import com.asyncconsumer.common.exception.CustomExceptionType

class IntegrationSiteNotFoundException (
    customExceptionType: CustomExceptionType = IntegrationSiteNotFoundExceptionType
): CustomException(customExceptionType)