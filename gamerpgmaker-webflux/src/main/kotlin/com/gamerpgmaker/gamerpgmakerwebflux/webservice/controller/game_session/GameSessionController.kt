package com.gamerpgmaker.gamerpgmakerwebflux.webservice.controller.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.GameSessionService
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("/api/game")
class GameSessionController(
    private val gameSessionService: GameSessionService
) {
    @GetMapping("/sessions")
    fun getAll(): Flux<GameSession> = gameSessionService.findAll()

    @GetMapping("/sessions/{id}")
    fun getById(@PathVariable id: UUID): Mono<GameSession> = gameSessionService.findById(id)
}