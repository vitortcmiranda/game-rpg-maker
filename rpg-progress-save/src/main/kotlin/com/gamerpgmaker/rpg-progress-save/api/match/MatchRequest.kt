package com.gamerpgmaker.`rpg-progress-save`.api.match

import java.time.Instant
import java.util.*

data class MatchRequest(
    val id: UUID = UUID.randomUUID(),
    val campaignId: UUID,
    var createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
) {

}
