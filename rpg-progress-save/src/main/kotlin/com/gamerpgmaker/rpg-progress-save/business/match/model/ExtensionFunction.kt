package com.gamerpgmaker.`rpg-progress-save`.business.match.model

import com.gamerpgmaker.`rpg-progress-save`.api.campaign.CampaignResponse
import com.gamerpgmaker.`rpg-progress-save`.api.match.MatchResponse
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.model.Campaign

fun Match.toGameSessionResponse() = MatchResponse(
    id = this.id!!,
    campaignId = this.campaignId,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)

fun Campaign.toGameResponse() = CampaignResponse(
    id = this.id!!,
    playersAmount = this.playersAmount,
    campaignName = this.campaignName,
    createdAt = this.createdAt,
)