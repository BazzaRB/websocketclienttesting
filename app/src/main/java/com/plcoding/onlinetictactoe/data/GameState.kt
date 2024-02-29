package com.plcoding.onlinetictactoe.data

import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    val connectedPlayers: List<Char> = emptyList()
) {
    var gameRunning = false


}
