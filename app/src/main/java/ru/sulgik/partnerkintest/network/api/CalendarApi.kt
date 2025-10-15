package ru.sulgik.partnerkintest.network.api

import ru.sulgik.partnerkintest.network.entity.CalendarData

interface CalendarApi {
    suspend fun loadCalendar(): CalendarData
}