package com.example.authentication.authentication_jwt.domain.core.exception

open class InvalidFormatException(dataType: String): Exception("$dataType is not valid type.")