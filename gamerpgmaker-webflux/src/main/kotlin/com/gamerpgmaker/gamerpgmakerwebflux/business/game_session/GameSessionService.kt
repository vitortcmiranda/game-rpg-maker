package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import reactor.core.publisher.Flux

interface GameSessionService {
    fun findAll(): Flux<GameSession>
}