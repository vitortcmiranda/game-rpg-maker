package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model

import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank

data class GameSessionRequest(
    val id: UUID = UUID.randomUUID(),
    val playersAmount: Int = 0,
    val hoursPlayed: Int = 0,
    var createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
    @field: NotBlank val sessionName: String
)
