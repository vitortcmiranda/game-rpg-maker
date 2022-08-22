package com.gamerpgmaker.gamerpgmakerwebflux.api.game_session

import java.time.Instant
import java.util.UUID

data class GameSessionResponse(
    val id: UUID,
    val playersAmount: Int,
    val hoursPlayed: Int,
    val createdAt: Instant,
    val sessionName: String? = null
)