package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.repository.GameSessionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class GameSessionServiceImpl(
    private val gameSessionRepository: GameSessionRepository
) : GameSessionService {
    override fun findAll(): Flux<GameSession> = gameSessionRepository.findAll()
}