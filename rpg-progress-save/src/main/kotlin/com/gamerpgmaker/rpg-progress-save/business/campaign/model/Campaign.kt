package com.gamerpgmaker.`rpg-progress-save`.business.campaign.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table("campaign")
data class Campaign(
    @Id
    val id: UUID? = null,
    @Column("campaign_name")
    val campaignName: String,
    val campaignMasterName: String,
    val playersAmount: Int,
    val status: CampaignStatus = CampaignStatus.CREATED,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)

enum class CampaignStatus {
    CREATED, ONGOING, STOPPED, FINISHED
}