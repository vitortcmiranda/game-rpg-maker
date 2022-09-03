package com.gamerpgmaker.gamerpgmakerwebflux.business.game.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table("game")
data class Game(
    @Id
    val id: UUID? = null,
    @Column("game_name")
    val gameName: String,
    val gameMasterName: String,
    val playersAmount: Int,
    val status: GameStatus = GameStatus.CREATED,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)

enum class GameStatus {
    CREATED, ONGOING, STOPPED, FINISHED
}