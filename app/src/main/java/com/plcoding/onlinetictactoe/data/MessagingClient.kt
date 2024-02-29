package com.plcoding.onlinetictactoe.data

import kotlinx.coroutines.flow.Flow


interface MessagingClient {
    fun getGameStateStream(): Flow<GameState>

    fun connectToServer()
    suspend fun sendMessage(action: Message)

    suspend fun sendAction(action: MakeTurn)

    suspend fun close()
}

