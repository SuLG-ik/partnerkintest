package ru.sulgik.partnerkintest.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import ru.sulgik.partnerkintest.common.data.ServerResponse
import ru.sulgik.partnerkintest.common.data.UnknownException
import ru.sulgik.partnerkintest.network.entity.EventData

class KtorEventApi(
    private val client: HttpClient,
) : EventApi {
    
    override suspend fun loadEvent(id: Long): EventData {
        val response = client.get("api_ios_test/view")
            .body<ServerResponse<EventData>>()
        
        return when {
            response.error != null -> {
                throw UnknownException("Server error: ${response.error.message ?: "Unknown error"}")
            }
            response.data == null -> {
                throw UnknownException("No data received from server")
            }
            else -> response.data
        }
    }
}
