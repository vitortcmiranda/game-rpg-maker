package com.gamerpgmaker.gamerpgmakerwebflux.business.campaign

import com.gamerpgmaker.`rpg-progress-save`.api.campaign.CampaignRequest
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.model.Campaign
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.repository.CampaignRepository
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.CampaignService
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.CampaignServiceImpl
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import reactor.kotlin.core.publisher.toMono
import reactor.kotlin.test.test
import java.util.*

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
internal class GameServiceImplTest() {
    private val campaignRepository: CampaignRepository = mockk()

    @InjectMocks
    private val campaignService: CampaignService = CampaignServiceImpl(campaignRepository)

    companion object {
        private val GAME_REQUEST = CampaignRequest(
            campaignName = "Teste", playersAmount = 3, campaignMasterName = "Master"
        )
    }

    @Test
    fun `should return game by id`() {
        val id = UUID.randomUUID()
        every { campaignRepository.findById(id) } answers { buildMatch(id = id).toMono() }

        campaignService.findById(id).test().assertNext {
            assertNotNull(it)
            it.id === id
        }.verifyComplete()

    }

    fun buildMatch(
        id: UUID = UUID.randomUUID(),
        campaignName: String = UUID.randomUUID().toString(),
        campaignMasterName: String = UUID.randomUUID().toString(),
        playersAmount: Int = 3,
    ): Campaign = Campaign(
        id,
        campaignName,
        campaignMasterName,
        playersAmount
    )
}