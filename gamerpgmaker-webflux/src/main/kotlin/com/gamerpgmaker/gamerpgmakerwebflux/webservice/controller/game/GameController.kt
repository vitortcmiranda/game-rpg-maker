package com.gamerpgmaker.gamerpgmakerwebflux.webservice.controller.game

import com.gamerpgmaker.gamerpgmakerwebflux.api.game.GameRequest
import com.gamerpgmaker.gamerpgmakerwebflux.api.game.GameResponse
import com.gamerpgmaker.gamerpgmakerwebflux.business.game.GameService
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.toGameResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("api/game")
class GameController(
    private val gameService: GameService
) {

    @PostMapping("")
    fun createGame(@RequestBody gameRequest: GameRequest): Mono<GameResponse> =
        gameService.createGame(gameRequest).flatMap { it.toGameResponse().toMono() }

}