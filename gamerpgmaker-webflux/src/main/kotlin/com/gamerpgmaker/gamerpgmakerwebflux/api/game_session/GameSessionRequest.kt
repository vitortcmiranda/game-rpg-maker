package com.gamerpgmaker.gamerpgmakerwebflux.api.game_session

import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank

data class GameSessionRequest(
    val id: UUID = UUID.randomUUID(),
    var createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
) {

}
