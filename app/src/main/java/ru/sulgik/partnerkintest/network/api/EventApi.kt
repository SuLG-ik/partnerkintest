package ru.sulgik.partnerkintest.network.api

import ru.sulgik.partnerkintest.network.entity.EventData

interface EventApi {
    suspend fun loadEvent(id: Long): EventData
}
