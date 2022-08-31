package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model

import com.gamerpgmaker.gamerpgmakerwebflux.api.game.GameResponse
import com.gamerpgmaker.gamerpgmakerwebflux.api.game_session.GameSessionResponse
import com.gamerpgmaker.gamerpgmakerwebflux.business.game.model.Game

fun GameSession.toGameSessionResponse() = GameSessionResponse(
    id = this.id!!,
    sessionName = this.sessionName,
    playersAmount = this.playerAmount,
    hoursPlayed = this.hoursPlayed,
    createdAt = this.createdAt,
)

fun Game.toGameResponse() = GameResponse(
    id = this.id!!,
    playersAmount = this.playersAmount,
    gameName = this.gameName,
    createdAt = this.createdAt
)