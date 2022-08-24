package com.gamerpgmaker.gamerpgmakerwebflux

import com.gamerpgmaker.gamerpgmakerwebflux.api.game_session.GameSessionRequest
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.GameSessionService
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.GameSessionServiceImpl
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.model.GameSession
import com.gamerpgmaker.gamerpgmakerwebflux.business.game_session.repository.GameSessionRepository
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import reactor.kotlin.test.test
import java.time.Instant
import java.util.*

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class GameSessionServiceTest(
) {
    private val gameSessionRepository: GameSessionRepository = mockk()

    @InjectMocks
    private val gameSessionService: GameSessionService = GameSessionServiceImpl(gameSessionRepository)

    companion object {
        private val GAME_SESSION = GameSessionRequest(
            playersAmount = 5,
            sessionName = "test"
        )
    }

    @Test
    fun `should get all gameSessions`() {
        every { gameSessionRepository.findAll() } answers { buildGameSession().toMono().toFlux() }

        gameSessionService.findAll()
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
        every { gameSessionRepository.deleteById(id) } answers {
            Mono.empty()
        }
        gameSessionService.deleteGameSession(id)
            .test()
            .verifyComplete()
    }

    @Test
    fun `should not delete gameSession when failed`() {
        val id = UUID.randomUUID()
        every { gameSessionRepository.findById(id) } answers { Mono.empty() }
        gameSessionService.deleteGameSession(id)
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


}