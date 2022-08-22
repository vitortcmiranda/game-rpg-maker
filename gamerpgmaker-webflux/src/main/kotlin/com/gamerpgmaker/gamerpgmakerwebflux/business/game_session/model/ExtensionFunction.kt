package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model

import com.gamerpgmaker.gamerpgmakerwebflux.api.game_session.GameSessionResponse

fun GameSession.toGameSessionResponse() = GameSessionResponse(
    id = this.id!!,
    sessionName = this.sessionName,
    playersAmount = this.playerAmount,
    hoursPlayed = this.hoursPlayed,
    createdAt = this.createdAt,
)