package ru.sulgik.partnerkintest.event.domain.usecase

import ru.sulgik.partnerkintest.event.domain.models.EventModel
import ru.sulgik.partnerkintest.event.domain.repository.EventRepository

class LoadEventUseCase(
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(id: Long): EventModel {
        return eventRepository.loadEvent(id)
    }
}