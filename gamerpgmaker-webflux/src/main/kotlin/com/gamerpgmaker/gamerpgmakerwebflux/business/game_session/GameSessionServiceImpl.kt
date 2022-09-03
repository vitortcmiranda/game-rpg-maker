package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game.GameService
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.*
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.repository.GameSessionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.time.Instant
import java.util.*

@Service
class GameSessionServiceImpl(
    private val gameService: GameService,
    private val gameSessionRepository: GameSessionRepository
) : GameSessionService {
    override fun findSessionsByGameId(gameId: UUID): Flux<GameSession> = gameSessionRepository.findByGameId(gameId)
    override fun findById(id: UUID): Mono<GameSession> = gameSessionRepository.findById(id).switchIfEmpty(
        Mono.error(
            NotFoundException("Game session with id $id was not found.")
        )
    )

    override fun createGameSession(gameId: UUID): Mono<GameSession> =
        gameService.findById(gameId).flatMap {
            findLastSession(gameId).flatMap { lastSession ->
                val canCreateNewSession = validateTimeBeforeNewSession(lastSession)

                if (!canCreateNewSession) {
                    lastSession
                }
                gameSessionRepository.save(
                    GameSession(
                        gameId = gameId
                    )
                )
            }
        }

    override fun deleteSessionsByGameId(gameId: UUID): Mono<Void> = gameSessionRepository.deleteByGameId(gameId)

    private fun findLastSession(gameId: UUID): Mono<GameSession> =
        gameSessionRepository.findLastSessionByGameId(gameId).toMono()


    private fun validateTimeBeforeNewSession(gameSession: GameSession): Boolean =
        Instant.now().isBefore(gameSession.createdAt.plusMillis(60 * 24))
}




