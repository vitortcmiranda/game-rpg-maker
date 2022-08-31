package com.gamerpgmaker.gamerpgmakerwebflux.api.game

data class GameRequest(
    public val gameName: String,
    public val playersAmount: Int,
    public val gameMasterName: String
)
