package com.gamerpgmaker.`rpg-progress-save`.business.match.model

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
data class NotFoundException(
    val msg: String
) : RuntimeException(msg)

@ResponseStatus(HttpStatus.BAD_REQUEST)
data class BadRequestException(
    val msg: String
) : RuntimeException(msg)