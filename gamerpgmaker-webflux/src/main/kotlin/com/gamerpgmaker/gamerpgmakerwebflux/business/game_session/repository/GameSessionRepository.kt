package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.repository

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface GameSessionRepository : ReactiveCrudRepository<GameSession, UUID> {
    fun findByGameId(gameId: UUID): Flux<GameSession>

    @Query("select * from game_session gs  where gs.game_id = :gameId order by created_at desc limit 1")
    fun findLastSessionByGameId(@Param("gameId") gameId: UUID): Flux<GameSession>
    fun deleteByGameId(gameId: UUID): Mono<Void>

}