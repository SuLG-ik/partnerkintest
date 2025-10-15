package ru.sulgik.partnerkintest.event.domain.repository

import ru.sulgik.partnerkintest.event.domain.models.EventModel

interface EventRepository {
    suspend fun loadEvent(id: Long): EventModel
}