package com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.repository

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

interface GameSessionRepository : ReactiveCrudRepository<GameSession, UUID> {
}