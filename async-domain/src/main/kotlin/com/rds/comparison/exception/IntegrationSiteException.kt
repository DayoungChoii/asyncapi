package com.rds.comparison.exception

import com.asyncsharedkernel.exception.CustomException
import com.asyncsharedkernel.exception.CustomExceptionType

class IntegrationSiteNotFoundException (
    customExceptionType: CustomExceptionType = IntegrationSiteNotFoundExceptionType
): CustomException(customExceptionType)