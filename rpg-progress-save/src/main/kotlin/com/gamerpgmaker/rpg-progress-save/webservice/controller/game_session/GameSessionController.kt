package com.gamerpgmaker.`rpg-progress-save`.webservice.controller.game_session

import com.gamerpgmaker.`rpg-progress-save`.business.match.MatchService
import com.gamerpgmaker.`rpg-progress-save`.api.match.MatchResponse
import com.gamerpgmaker.`rpg-progress-save`.business.match.model.toGameSessionResponse
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
    private val matchService: MatchService,
) {
    @GetMapping("")
    fun getAll(@PathVariable gameId: UUID): Flux<MatchResponse> =
        matchService.findMatchByCampaignId(gameId).map { it.toGameSessionResponse() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): Mono<MatchResponse> = matchService.findById(id).flatMap {
        it.toGameSessionResponse().toMono()
    }


    @PostMapping("")
    fun createGameSession(@PathVariable gameId: UUID): Mono<MatchResponse> =
        matchService.createMatch(gameId).flatMap { it.toGameSessionResponse().toMono() }


    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteGameSession(@PathVariable gameId: UUID): Mono<Void> =
        matchService.deleteMatchByGameId(gameId)
}