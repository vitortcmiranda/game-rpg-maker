package com.gamerpgmaker.gamerpgmakerwebflux.webservice.controller.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.GameSessionService
import com.gamerpgmaker.gamerpgmakerwebflux.api.game_session.GameSessionResponse
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.toGameSessionResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.UUID

@RestController
@RequestMapping("/api/game/{gameId}/sessions")
class GameSessionController(
    private val gameSessionService: GameSessionService,
) {
    @GetMapping("")
    fun getAll(@PathVariable gameId: UUID): Flux<GameSessionResponse> =
        gameSessionService.findSessionsByGameId(gameId).map { it.toGameSessionResponse() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): Mono<GameSessionResponse> = gameSessionService.findById(id).flatMap {
        it.toGameSessionResponse().toMono()
    }


    @PostMapping("")
    fun createGameSession(@PathVariable gameId: UUID): Mono<GameSessionResponse> =
        gameSessionService.createGameSession(gameId).flatMap { it.toGameSessionResponse().toMono() }


    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteGameSession(@PathVariable gameId: UUID): Mono<Void> =
        gameSessionService.deleteSessionsByGameId(gameId)
}