package com.gamerpgmaker.gamerpgmakerwebflux.business.game

import com.gamerpgmaker.gamerpgmakerwebflux.api.game.GameRequest
import com.gamerpgmaker.gamerpgmakerwebflux.business.game.model.Game
import com.gamerpgmaker.gamerpgmakerwebflux.business.game.repository.GameRepository
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.BadRequestException
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.*

@Service
class GameServiceImpl(
    private val gameRepository: GameRepository
) : GameService {

    override fun findById(id: UUID): Mono<Game> = gameRepository.findById(id).switchIfEmpty(
        Mono.error(NotFoundException("Game with id $id was not found"))
    )

    override fun createGame(request: GameRequest): Mono<Game> =
        gameRepository.findByGameNameAndGameMasterName(request.gameName, request.gameMasterName)
            .flatMap<Game?> {
                Mono.error(BadRequestException("Game with name ${request.gameName} hosted by ${request.gameMasterName} already exists."))
            }
            .switchIfEmpty {
                gameRepository.save(
                    Game(
                        gameName = request.gameName,
                        playersAmount = request.playersAmount,
                        gameMasterName = request.gameMasterName
                    )
                )
            }

    override fun findAll(): Flux<Game> = gameRepository.findAll()

    override fun deleteGame(id: UUID): Mono<Void> {
        TODO("Not yet implemented")
    }
}