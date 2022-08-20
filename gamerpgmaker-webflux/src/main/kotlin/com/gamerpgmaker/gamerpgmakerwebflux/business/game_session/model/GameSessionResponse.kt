package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model

import java.time.Instant
import java.util.UUID

data class GameSessionResponse(
    val id: UUID,
    val playersAmount: Int,
    val hoursPlayed: Int,
    val createdAt: Instant,
    val sessionName: String? = null
)