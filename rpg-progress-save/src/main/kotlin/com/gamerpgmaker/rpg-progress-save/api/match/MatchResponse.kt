package com.gamerpgmaker.`rpg-progress-save`.api.match

import java.time.Instant
import java.util.UUID

data class MatchResponse(
    val id: UUID,
    val campaignId: UUID,
    val createdAt: Instant,
    val updatedAt: Instant,
)