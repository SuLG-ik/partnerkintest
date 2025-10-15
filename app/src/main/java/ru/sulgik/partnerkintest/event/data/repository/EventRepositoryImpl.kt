package ru.sulgik.partnerkintest.event.data.repository

import ru.sulgik.partnerkintest.event.data.mapper.EventMapper
import ru.sulgik.partnerkintest.event.domain.models.EventModel
import ru.sulgik.partnerkintest.event.domain.repository.EventRepository
import ru.sulgik.partnerkintest.network.api.EventApi

class EventRepositoryImpl(
    private val eventApi: EventApi,
) : EventRepository {
    override suspend fun loadEvent(id: Long): EventModel {
        val data = eventApi.loadEvent(id)
        return EventMapper(data)
    }
}