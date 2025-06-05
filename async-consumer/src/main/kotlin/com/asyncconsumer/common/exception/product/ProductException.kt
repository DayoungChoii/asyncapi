package com.asyncconsumer.common.exception.product

import com.asyncconsumer.common.exception.CustomException
import com.asyncconsumer.common.exception.CustomExceptionType

class ProductNotFoundException(
    customExceptionType: CustomExceptionType = ProductNotFoundExceptionType,
): CustomException(customExceptionType)