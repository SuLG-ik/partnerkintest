package ru.sulgik.partnerkintest.calendar.data.repository

import ru.sulgik.partnerkintest.calendar.data.mapper.CalendarMapper
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemModel
import ru.sulgik.partnerkintest.calendar.domain.repository.CalendarRepository
import ru.sulgik.partnerkintest.network.api.CalendarApi

class CalendarRepositoryImpl(
    private val calendarApi: CalendarApi,
) : CalendarRepository {
    override suspend fun loadCalendar(): List<CalendarItemModel> {
        val data = calendarApi.loadCalendar()
        return CalendarMapper(data)
    }
}