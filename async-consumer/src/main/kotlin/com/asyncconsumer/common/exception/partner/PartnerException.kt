package com.asyncconsumer.common.exception.partner

import com.asyncconsumer.common.exception.CustomException
import com.asyncconsumer.common.exception.CustomExceptionType

class PartnerNotFoundException(
    customExceptionType: CustomExceptionType = PartnerNotFoundExceptionType
): CustomException(customExceptionType)