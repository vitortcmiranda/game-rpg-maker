package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.api.game_session.GameSessionRequest
import com.gamerpgmaker.gamerpgmakerwebflux.api.game_session.GameSessionResponse
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.*
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.repository.GameSessionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.*

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

    override fun deleteGameSession(id: UUID): Mono<Void> = findById(id).flatMap { gameSessionRepository.delete(it) }


//        gameSessionRepository.findById(id).flatMap {
//            gameSessionRepository.deleteById(id)
//        }.switchIfEmpty(
//            Mono.error(
//                NotFoundException("Game session with id $id was not found.")
//            )
//        )

    override fun updateSession(id: UUID, request: GameSessionRequest): Mono<GameSessionResponse> =
        findById(id).flatMap { foundSession ->
            gameSessionRepository.save(
                GameSession(
                    playerAmount = request.playersAmount,
                    hoursPlayed = request.hoursPlayed,
                    updatedAt = request.updatedAt,
                    sessionName = request.sessionName,
                    id = foundSession.id,
                    createdAt = foundSession.createdAt,
                    status = foundSession.status
                )
            ).map { it.toGameSessionResponse() }
        }.switchIfEmpty {
            Mono.error(BadRequestException("Game session id '${request.id}' does not exists."))
        }

}