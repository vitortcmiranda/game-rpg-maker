package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table("game_session")
data class GameSession(
    @Id
    val id: UUID? = null, //postgress will create the id for us with gen_random_uuid ()
    val playerAmount: Int,
    val hoursPlayed: Int,
    var createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
)
