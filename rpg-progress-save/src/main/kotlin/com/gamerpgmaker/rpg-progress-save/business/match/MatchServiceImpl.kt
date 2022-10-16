package com.gamerpgmaker.`rpg-progress-save`.business.match

import com.gamerpgmaker.`rpg-progress-save`.business.campaign.CampaignService
import com.gamerpgmaker.gamerpgmakerwebflux.business.match.model.*
import com.gamerpgmaker.`rpg-progress-save`.business.match.repository.MatchRepository
import com.gamerpgmaker.`rpg-progress-save`.business.match.model.Match
import com.gamerpgmaker.`rpg-progress-save`.business.match.model.NotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.time.Instant
import java.util.*

@Service
class MatchServiceImpl(
    private val campaignService: CampaignService,
    private val matchRepository: MatchRepository
) : MatchService {
    override fun findMatchByCampaignId(gameId: UUID): Flux<Match> = matchRepository.findByCampaignId(gameId)
    override fun findById(id: UUID): Mono<Match> = matchRepository.findById(id).switchIfEmpty(
        Mono.error(
            NotFoundException("Game session with id $id was not found.")
        )
    )

    override fun createMatch(campaignId: UUID): Mono<Match> =
        campaignService.findById(campaignId).flatMap {
            findLastMatch(campaignId).flatMap { lastMatch ->
                val canCreateNewSession = validateTimeBeforeNewSession(lastMatch)

                if (!canCreateNewSession) {
                    lastMatch
                }
                matchRepository.save(
                    Match(
                        campaignId = campaignId
                    )
                )
            }
        }

    override fun deleteMatchByGameId(gameId: UUID): Mono<Void> = matchRepository.deleteByCampaignId(gameId)

    private fun findLastMatch(gameId: UUID): Mono<Match> =
        matchRepository.findLastMatchByCampaignId(gameId).toMono()


    private fun validateTimeBeforeNewSession(gameSession: Match): Boolean =
        Instant.now().isBefore(gameSession.createdAt.plusMillis(60 * 24))
}




