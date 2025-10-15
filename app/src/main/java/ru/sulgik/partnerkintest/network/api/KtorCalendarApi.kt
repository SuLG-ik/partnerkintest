package ru.sulgik.partnerkintest.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.sulgik.partnerkintest.common.data.ServerResponse
import ru.sulgik.partnerkintest.common.data.UnknownException
import ru.sulgik.partnerkintest.network.entity.CalendarData

class KtorCalendarApi(
    private val client: HttpClient,
) : CalendarApi {
    
    override suspend fun loadCalendar(): CalendarData {
        val response = client.get("api_ios_test/list")
            .body<ServerResponse<CalendarData>>()
        
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