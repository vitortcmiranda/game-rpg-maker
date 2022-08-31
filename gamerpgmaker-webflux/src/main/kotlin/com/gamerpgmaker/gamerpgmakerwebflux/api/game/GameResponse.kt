package com.gamerpgmaker.gamerpgmakerwebflux.api.game

import java.time.Instant
import java.util.*

data class GameResponse(
    val id: UUID,
    val playersAmount: Int,
    val gameName: String,
    val createdAt: Instant,
)
