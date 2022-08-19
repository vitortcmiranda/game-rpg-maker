package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.BadRequestException
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSessionRequest
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.NotFoundException
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.repository.GameSessionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.UUID

@Service
class GameSessionServiceImpl(
    private val gameSessionRepository: GameSessionRepository
) : GameSessionService {
    override fun findAll(): Flux<GameSession> = gameSessionRepository.findAll()
    override fun findById(id: UUID): Mono<GameSession> = gameSessionRepository.findById(id).switchIfEmpty(
        Mono.error(
            NotFoundException("Game session with id $id was not found.")
        )
    )

    override fun createGameSession(request: GameSessionRequest): Mono<GameSession> =
        gameSessionRepository.findBySessionName(request.sessionName).flatMap<GameSession?> {
            Mono.error(BadRequestException("Game session name '${request.sessionName}' already taken."))
        }.switchIfEmpty {
            gameSessionRepository.save(
                GameSession(
                    playerAmount = request.playersAmount,
                    hoursPlayed = request.hoursPlayed,
                    sessionName = request.sessionName
                )
            )
        }


}