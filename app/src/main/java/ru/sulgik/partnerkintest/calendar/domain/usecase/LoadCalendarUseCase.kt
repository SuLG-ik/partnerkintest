package ru.sulgik.partnerkintest.calendar.domain.usecase

import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemModel
import ru.sulgik.partnerkintest.calendar.domain.repository.CalendarRepository

class LoadCalendarUseCase(
    private val calendarRepository: CalendarRepository,
) {
    suspend operator fun invoke(): List<CalendarItemModel> {
        return calendarRepository.loadCalendar()
    }
}