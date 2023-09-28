package com.example.authentication.authentication_jwt.config.exception

open class BusinessException(
    val errorCode: ErrorCode
): RuntimeException() {
}