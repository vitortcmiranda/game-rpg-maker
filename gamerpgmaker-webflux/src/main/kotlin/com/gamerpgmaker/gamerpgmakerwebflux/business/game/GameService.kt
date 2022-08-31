package com.gamerpgmaker.gamerpgmakerwebflux.business.game

import com.gamerpgmaker.gamerpgmakerwebflux.api.game.GameRequest
import com.gamerpgmaker.gamerpgmakerwebflux.api.game.GameResponse
import com.gamerpgmaker.gamerpgmakerwebflux.business.game.model.Game
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface GameService {
    fun createGame(gameRequest: GameRequest): Mono<Game>
    fun findAll(): Flux<Game>
    fun findById(id: UUID): Mono<Game>
    fun deleteGame(id: UUID): Mono<Void>
}
