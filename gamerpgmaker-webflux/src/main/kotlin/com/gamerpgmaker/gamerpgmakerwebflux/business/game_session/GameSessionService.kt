package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface GameSessionService {
    fun findAll(): Flux<GameSession>

    fun findById(id: UUID): Mono<GameSession>
}