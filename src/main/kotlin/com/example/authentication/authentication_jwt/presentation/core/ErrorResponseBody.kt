package com.example.authentication.authentication_jwt.presentation.core

import com.example.authentication.authentication_jwt.config.exception.ErrorCode
import java.time.LocalDateTime

data class ErrorResponseBody(
    val message: String,
    val status: Int,
    val code : String,
    val error: String,
) {
    val timestamp: LocalDateTime = LocalDateTime.now()

    constructor(errorCode: ErrorCode, error: String = "error"): this(
        message=errorCode.message,
        status = errorCode.status,
        code = errorCode.code,
        error = error
    )
}