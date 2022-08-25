package com.gamerpgmaker.gamerpgmakerwebflux.webservice.controller.game_session

import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.GameSessionService
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import com.gamerpgmaker.gamerpgmakerwebflux.api.game_session.GameSessionRequest
import com.gamerpgmaker.gamerpgmakerwebflux.api.game_session.GameSessionResponse
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.toGameSessionResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/api/game/sessions")
class GameSessionController(
    private val gameSessionService: GameSessionService
) {
    @GetMapping("")
    fun getAll(): Flux<GameSessionResponse> = gameSessionService.findAll().map { it.toGameSessionResponse() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): Mono<GameSessionResponse> = gameSessionService.findById(id).flatMap {
        it.toGameSessionResponse().toMono()
    }

    @PostMapping("")
    fun createGameSession(@Valid @RequestBody gameSessionRequest: GameSessionRequest): Mono<GameSessionResponse> =
        gameSessionService.createGameSession(gameSessionRequest).flatMap { it.toGameSessionResponse().toMono() }


    @PutMapping("/{id}")
    fun updateGameSession(
        @Valid @RequestBody gameSessionRequest: GameSessionRequest,
        @PathVariable id: UUID
    ): Mono<GameSessionResponse> = gameSessionService.updateSession(id, gameSessionRequest)


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteGameSession(@PathVariable id: UUID): Mono<Void> =
        gameSessionService.deleteGameSession(id)
}