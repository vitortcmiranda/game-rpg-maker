package com.gamerpgmaker.gamerpgmakerwebflux.webservice.controller.game_characters

import com.gamerpgmaker.`rpg-progress-save`.webservice.controller.game_characters.GameCharacterController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import java.util.*
import kotlin.collections.HashMap

@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [GameCharacterController::class])
class GameCharacterControllerTest(
    @Autowired private val client: WebTestClient
) {

    @Test
    internal fun `should get`() {
        val pathVars = HashMap<String, String>()
        pathVars["gameId"] = UUID.randomUUID().toString()
        client.get().uri { builder ->
            builder.path("/api/game/{gameId}/characters").build(pathVars)
        }
            .exchange().expectStatus().isOk.expectBody<String>().isEqualTo("ok")
    }

    /**
    @Test
    internal fun get() {
    client.get().uri("/api/game/{gameId}/characters").exchange().expectStatus().isOk
    }
     **/
}