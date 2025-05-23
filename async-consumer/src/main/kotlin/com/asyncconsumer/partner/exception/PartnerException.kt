package com.asyncconsumer.partner.exception

import com.asyncconsumer.common.exception.CustomException
import com.asyncconsumer.common.exception.CustomExceptionType

class PartnerNotFoundException(
    customExceptionType: CustomExceptionType = PartnerNotFoundExceptionType,
): CustomException(customExceptionType)