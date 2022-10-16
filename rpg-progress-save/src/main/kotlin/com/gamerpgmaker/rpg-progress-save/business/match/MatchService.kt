package com.gamerpgmaker.`rpg-progress-save`.business.match

import com.gamerpgmaker.`rpg-progress-save`.business.match.model.Match
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface MatchService {
    fun findMatchByCampaignId(gameId: UUID): Flux<Match>
    fun findById(id: UUID): Mono<Match>
    fun createMatch(gameId: UUID): Mono<Match>
    fun deleteMatchByGameId(gameId: UUID): Mono<Void>
}