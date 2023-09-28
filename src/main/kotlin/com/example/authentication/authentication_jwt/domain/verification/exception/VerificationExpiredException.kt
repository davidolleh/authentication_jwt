package com.example.authentication.authentication_jwt.domain.verification.exception

import com.example.authentication.authentication_jwt.config.exception.BusinessException
import com.example.authentication.authentication_jwt.config.exception.ErrorCode

class VerificationExpiredException(): BusinessException(
    errorCode = ErrorCode.VERIFICATION_EXPIRED
)