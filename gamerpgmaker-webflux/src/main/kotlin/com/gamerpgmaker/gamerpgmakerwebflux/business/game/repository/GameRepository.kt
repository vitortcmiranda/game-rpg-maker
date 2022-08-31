package com.gamerpgmaker.gamerpgmakerwebflux.business.game.repository

import com.gamerpgmaker.gamerpgmakerwebflux.business.game.model.Game
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import java.util.UUID

interface GameRepository : ReactiveCrudRepository<Game, UUID> {
    fun findByGameNameAndGameMasterName(gameName: String, gameMasterName: String): Mono<Game>

}
