package com.gamerpgmaker.gamerpgmakerwebflux.webservice.controller.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.GameSessionService
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSessionRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("/api/game/sessions")
class GameSessionController(
    private val gameSessionService: GameSessionService
) {
    @GetMapping("")
    fun getAll(): Flux<GameSession> = gameSessionService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): Mono<GameSession> = gameSessionService.findById(id)

    @PostMapping("")
    fun createGameSession(@RequestBody gameSessionRequest: GameSessionRequest): Mono<GameSession> =
        gameSessionService.createGameSession(gameSessionRequest)
}