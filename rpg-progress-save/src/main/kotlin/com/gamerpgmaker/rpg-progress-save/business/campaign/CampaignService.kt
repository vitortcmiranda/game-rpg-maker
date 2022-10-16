package com.gamerpgmaker.`rpg-progress-save`.business.campaign

import com.gamerpgmaker.`rpg-progress-save`.api.campaign.CampaignRequest
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.model.Campaign
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface CampaignService {
    fun createGame(campaignRequest: CampaignRequest): Mono<Campaign>
    fun findAll(): Flux<Campaign>
    fun findById(id: UUID): Mono<Campaign>
    fun deleteGame(id: UUID): Mono<Void>
}
