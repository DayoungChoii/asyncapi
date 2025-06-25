package com.rds.product.exception

import com.asyncsharedkernel.exception.CustomException
import com.asyncsharedkernel.exception.CustomExceptionType

class ProductNotFoundException(
    customExceptionType: CustomExceptionType = ProductNotFoundExceptionType,
): CustomException(customExceptionType)