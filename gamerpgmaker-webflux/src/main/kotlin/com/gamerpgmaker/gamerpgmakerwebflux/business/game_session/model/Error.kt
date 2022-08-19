package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
data class NotFoundException(
    val msg: String
) : RuntimeException(msg)