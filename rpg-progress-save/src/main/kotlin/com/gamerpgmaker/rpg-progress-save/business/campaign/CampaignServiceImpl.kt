package com.gamerpgmaker.`rpg-progress-save`.business.campaign

import com.gamerpgmaker.`rpg-progress-save`.api.campaign.CampaignRequest
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.model.Campaign
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.repository.CampaignRepository
import com.gamerpgmaker.`rpg-progress-save`.business.match.model.BadRequestException
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.*

@Service
class CampaignServiceImpl(
    private val campaignRepository: CampaignRepository
) : CampaignService {

    override fun findById(id: UUID): Mono<Campaign> = campaignRepository.findById(id).switchIfEmpty(
        Mono.error(NotFoundException("Game with id $id was not found"))
    )

    override fun createGame(request: CampaignRequest): Mono<Campaign> =
        campaignRepository.findByCampaignNameAndGameMasterName(request.campaignName, request.campaignMasterName)
            .flatMap<Campaign?> {
                Mono.error(BadRequestException("Game with name ${request.campaignName} hosted by ${request.campaignMasterName} already exists."))
            }
            .switchIfEmpty {
                campaignRepository.save(
                    Campaign(
                        campaignName = request.campaignName,
                        playersAmount = request.playersAmount,
                        campaignMasterName = request.campaignMasterName
                    )
                )
            }

    override fun findAll(): Flux<Campaign> = campaignRepository.findAll()

    override fun deleteGame(id: UUID): Mono<Void> {
        TODO("Not yet implemented")
    }
}