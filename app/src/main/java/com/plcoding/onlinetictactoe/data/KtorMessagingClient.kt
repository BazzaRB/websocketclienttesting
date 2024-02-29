package com.plcoding.onlinetictactoe.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.setCookie
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KtorMessagingClient (
    private val client: HttpClient
): MessagingClient {

    private var session: WebSocketSession? = null
    private var authCookie: String? = null
 // TODO FIX THIS PART; WHERE I SEND A REQUEST FOR COOKIE TO ADD BELLOW, THEN IN MAIN ACTIVITY MAKE A BUTTOIN TO CONNECT TO SERVER
    private suspend fun ensureAuthenticated() {
        if (authCookie == null) {
            val response = client.request("http://localhost:5678/auth?name=YourName")

            authCookie = response.setCookie().firstOrNull()?.value
            if (authCookie == null) {
                throw IllegalStateException("Authentication failed")
            }
        }
    }

    override fun getGameStateStream(): Flow<GameState> {

        return flow {

            ensureAuthenticated()

            session = client.webSocketSession {
                url("ws://localhost:5678/room")
                parameter("auth", authCookie)
            }
            val gameStates = session!!
                .incoming
                .consumeAsFlow()
                .filterIsInstance<Frame.Text>()
                .mapNotNull { Json.decodeFromString<GameState>(it.readText()) }

            emitAll(gameStates)
        }
    }

    override fun connectToServer() {
        TODO("Not yet implemented")
    }

    /*
    override fun connectToServer(): Flow<GameState> {

        return flow {

            ensureAuthenticated()

            session = client.webSocketSession {
                url("ws://localhost:5678/room")
                parameter("auth", authCookie)
            }
            val gameStates = session!!
                .incoming
                .consumeAsFlow()
                .filterIsInstance<Frame.Text>()
                .mapNotNull { Json.decodeFromString<GameState>(it.readText()) }

            emitAll(gameStates)
        }
    } */

    override suspend fun sendMessage(action: Message) {
        session?.outgoing?.send(
            Frame.Text("message#${Json.encodeToString(action)}")
        )
    }

    override suspend fun close() {
        session?.close()
        session = null
    }

    override suspend fun sendAction(action: MakeTurn) {
        session?.outgoing?.send(
            Frame.Text("make_turn#${Json.encodeToString(action)}")
        )
    }




}