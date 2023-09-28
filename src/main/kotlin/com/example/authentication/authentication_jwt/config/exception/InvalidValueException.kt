package com.example.authentication.authentication_jwt.config.exception

open class InvalidValueException() : BusinessException(
    errorCode = ErrorCode.INVALID_INPUT_VALUE
)