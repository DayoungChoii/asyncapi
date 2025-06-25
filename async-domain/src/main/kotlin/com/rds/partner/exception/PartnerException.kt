package com.rds.partner.exception

import com.asyncsharedkernel.exception.CustomException
import com.asyncsharedkernel.exception.CustomExceptionType

class PartnerNotFoundException(
    customExceptionType: CustomExceptionType = PartnerNotFoundExceptionType
): CustomException(customExceptionType)