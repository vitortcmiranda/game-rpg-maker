package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSessionRequest
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSessionResponse
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface GameSessionService {
    fun findAll(): Flux<GameSession>

    fun findById(id: UUID): Mono<GameSession>
    fun createGameSession(gameSessionRequest: GameSessionRequest): Mono<GameSession>
    fun deleteGameSession(id: UUID): Mono<Void>
    fun updateSession(id: UUID, gameSessionRequest: GameSessionRequest): Mono<GameSession>
}