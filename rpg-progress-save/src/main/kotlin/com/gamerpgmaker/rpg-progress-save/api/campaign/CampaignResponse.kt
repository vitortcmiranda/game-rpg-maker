package com.gamerpgmaker.`rpg-progress-save`.api.campaign

import java.time.Instant
import java.util.*

data class CampaignResponse(
    val id: UUID,
    val playersAmount: Int,
    val campaignName: String,
    val createdAt: Instant,
)
