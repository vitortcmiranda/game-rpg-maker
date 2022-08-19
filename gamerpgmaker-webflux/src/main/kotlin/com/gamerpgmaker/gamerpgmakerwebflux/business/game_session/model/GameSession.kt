package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table("game_session")
data class GameSession(
    @Id
    val id: UUID,
    val playerAmount: Int = 0,
    val hoursPlayed: Int = 0,
    var createdAt: Instant,
    val updatedAt: Instant
)
