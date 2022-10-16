package com.gamerpgmaker.`rpg-progress-save`.business.match.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table("match")
data class Match(
    @Id
    val id: UUID? = null, //postgress will create the id for us with gen_random_uuid ()
    val campaignId: UUID,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
