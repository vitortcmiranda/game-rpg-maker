package com.gamerpgmaker.`rpg-progress-save`.webservice.controller.game

import com.gamerpgmaker.`rpg-progress-save`.api.campaign.CampaignRequest
import com.gamerpgmaker.`rpg-progress-save`.api.campaign.CampaignResponse
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.CampaignService
import com.gamerpgmaker.`rpg-progress-save`.business.match.model.toGameResponse
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@RestController
@RequestMapping("api/game")
class GameController(
    private val campaignService: CampaignService
) {
    @PostMapping("")
    fun createGame(@RequestBody campaignRequest: CampaignRequest): Mono<CampaignResponse> =
        campaignService.createGame(campaignRequest).flatMap { it.toGameResponse().toMono() }


    @GetMapping("id/{id}")
    fun getGameById(@RequestParam id: UUID): Mono<CampaignResponse> =
        campaignService.findById(id).flatMap { it.toGameResponse().toMono() }

}