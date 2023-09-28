package com.example.authentication.authentication_jwt.config.exception

class EntityNotFoundException(): BusinessException(
    errorCode = ErrorCode.ENTITY_NOT_FOUND
)