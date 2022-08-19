package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.repository

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import java.util.UUID

interface GameSessionRepository : ReactiveCrudRepository<GameSession, UUID> {
    fun findBySessionName(sessionName: String): Mono<GameSession>
}