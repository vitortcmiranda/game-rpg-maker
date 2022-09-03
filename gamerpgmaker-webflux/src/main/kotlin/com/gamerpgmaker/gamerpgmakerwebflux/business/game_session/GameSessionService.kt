package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface GameSessionService {
    fun findSessionsByGameId(gameId: UUID): Flux<GameSession>
    fun findById(id: UUID): Mono<GameSession>
    fun createGameSession(gameId: UUID): Mono<GameSession>
    fun deleteSessionsByGameId(gameId: UUID): Mono<Void>
}