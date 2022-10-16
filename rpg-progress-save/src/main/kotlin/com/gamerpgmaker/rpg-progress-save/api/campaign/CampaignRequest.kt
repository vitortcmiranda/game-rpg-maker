package com.gamerpgmaker.`rpg-progress-save`.api.campaign

data class CampaignRequest(
    public val campaignName: String,
    public val playersAmount: Int,
    public val campaignMasterName: String
)
