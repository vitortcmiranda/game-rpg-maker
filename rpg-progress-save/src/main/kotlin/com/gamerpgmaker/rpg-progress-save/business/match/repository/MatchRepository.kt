package com.gamerpgmaker.`rpg-progress-save`.business.match.repository

import com.gamerpgmaker.`rpg-progress-save`.business.match.model.Match
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface MatchRepository : ReactiveCrudRepository<Match, UUID> {
    fun findByCampaignId(gameId: UUID): Flux<Match>

    @Query("select * from 'match' gs  where gs.campaign_id = :campaignId order by created_at desc limit 1")
    fun findLastMatchByCampaignId(@Param("campaignId") campaignId: UUID): Flux<Match>
    fun deleteByCampaignId(gameId: UUID): Mono<Void>

}