package com.gamerpgmaker.`rpg-progress-save`.webservice.controller.game_characters

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/api/game/{gameId}/characters")
class GameCharacterController {

    @GetMapping("")
    fun getCharacters(@PathVariable gameId: UUID) = Mono.just("ok")
}