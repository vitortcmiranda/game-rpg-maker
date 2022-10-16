package com.gamerpgmaker.gamerpgmakerwebflux

import com.gamerpgmaker.`rpg-progress-save`.api.match.MatchRequest
import com.gamerpgmaker.`rpg-progress-save`.business.campaign.CampaignService
import com.gamerpgmaker.`rpg-progress-save`.business.match.MatchService
import com.gamerpgmaker.`rpg-progress-save`.business.match.MatchServiceImpl
import com.gamerpgmaker.`rpg-progress-save`.business.match.repository.MatchRepository
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import java.util.*

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class MatchServiceTest(
) {
    private val matchRepository: MatchRepository = mockk()
    private val campaignService: CampaignService = mockk()

    @InjectMocks
    private val matchService: MatchService = MatchServiceImpl(campaignService, matchRepository)


    companion object {
        private val GAME_SESSION = MatchRequest(
            gameId = UUID.randomUUID()
        )
    }
    /**
    @Test
    fun `should get all gameSessions`() {
    every { gameSessionRepository.findAll() } answers { buildGameSession().toMono().toFlux() }

    gameSessionService.findSessionsByGameId()
    .test()
    .assertNext {
    Assertions.assertNotNull(it)
    it.sessionName === "test"
    }
    .verifyComplete()
    }

    @Test
    fun `should get gameSession by id`() {
    val id = UUID.randomUUID()
    every { gameSessionRepository.findById(id) } answers { buildGameSession(id).toMono() }

    gameSessionService.findById(id)
    .test()
    .assertNext {
    Assertions.assertNotNull(it)
    it.id === id
    }
    .verifyComplete()
    }

    @Test
    fun `should not found gameSession by id`() {
    val id = UUID.randomUUID()
    every { gameSessionRepository.findById(id) } answers { Mono.empty() }

    gameSessionService.findById(id)
    .test()
    .expectError()
    }

    @Test
    fun `should create gameSession when successful`() {
    val id = UUID.randomUUID()
    every { gameSessionRepository.findBySessionName(GAME_SESSION.sessionName) } answers { Mono.empty() }
    every { gameSessionRepository.save(any()) } answers {
    buildGameSession(
    playersAmount = GAME_SESSION.playersAmount,
    sessionName = GAME_SESSION.sessionName
    ).toMono()
    }
    gameSessionService.createGameSession(GAME_SESSION)
    .test()
    .assertNext {
    Assertions.assertNotNull(it)
    Assertions.assertEquals(it.sessionName, GAME_SESSION.sessionName)
    Assertions.assertEquals(it.playerAmount, GAME_SESSION.playersAmount)
    }
    .verifyComplete()
    }

    @Test
    fun `should delete gameSession when successful`() {
    val id = UUID.randomUUID()
    every { gameSessionRepository.findById(id) } answers { buildGameSession().toMono() }
    every { gameSessionRepository.delete(any()) } answers {
    Mono.empty()
    }
    gameSessionService.deleteSessionsByGameId(id)
    .test()
    .verifyComplete()
    }

    @Test
    fun `should not delete gameSession when failed`() {
    val id = UUID.randomUUID()
    every { gameSessionRepository.findById(id) } answers { Mono.empty() }
    gameSessionService.deleteSessionsByGameId(id)
    .test()
    .expectError()
    }

    fun buildGameSession(
    id: UUID = UUID.randomUUID(),
    playersAmount: Int = 0,
    sessionName: String = UUID.randomUUID().toString()
    ): GameSession = GameSession(
    id = id,
    playerAmount = playersAmount,
    hoursPlayed = 3,
    createdAt = Instant.now(),
    sessionName = "test"
    )
     **/
}