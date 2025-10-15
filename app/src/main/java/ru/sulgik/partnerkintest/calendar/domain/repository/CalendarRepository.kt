package ru.sulgik.partnerkintest.calendar.domain.repository

import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemModel

interface CalendarRepository {
    suspend fun loadCalendar(): List<CalendarItemModel>
}