package com.gamerpgmaker.gamerpgmakerwebflux.webservice.controller.game

import com.gamerpgmaker.gamerpgmakerwebflux.api.game.GameRequest
import com.gamerpgmaker.gamerpgmakerwebflux.api.game.GameResponse
import com.gamerpgmaker.gamerpgmakerwebflux.business.game.GameService
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.toGameResponse
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@RestController
@RequestMapping("api/game")
class GameController(
    private val gameService: GameService
) {
    @PostMapping("")
    fun createGame(@RequestBody gameRequest: GameRequest): Mono<GameResponse> =
        gameService.createGame(gameRequest).flatMap { it.toGameResponse().toMono() }


    @GetMapping("id/{id}")
    fun getGameById(@RequestParam id: UUID): Mono<GameResponse> =
        gameService.findById(id).flatMap { it.toGameResponse().toMono() }

}