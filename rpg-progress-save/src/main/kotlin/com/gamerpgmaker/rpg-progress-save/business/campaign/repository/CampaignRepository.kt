package com.gamerpgmaker.`rpg-progress-save`.business.campaign.repository

import com.gamerpgmaker.`rpg-progress-save`.business.campaign.model.Campaign
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import java.util.UUID

interface CampaignRepository : ReactiveCrudRepository<Campaign, UUID> {
    fun findByCampaignNameAndGameMasterName(campaignName: String, campaignMasterName: String): Mono<Campaign>

}
