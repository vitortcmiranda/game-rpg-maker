package com.gamerpgmaker.gamerpgmakerwebflux.api.game_session

import java.time.Instant
import java.util.UUID

data class GameSessionResponse(
    val id: UUID,
    val gameId: UUID,
    val createdAt: Instant,
    val updatedAt: Instant,
)