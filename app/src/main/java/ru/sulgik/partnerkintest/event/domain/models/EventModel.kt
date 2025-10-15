package ru.sulgik.partnerkintest.event.domain.models

import kotlinx.collections.immutable.ImmutableList
import java.time.LocalDate

data class EventModel(
    val id: Long,
    val name: String,
    val type: String,
    val imageUrl: String,
    val imagePreviewUrl: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val city: String,
    val country: String,
    val categories: ImmutableList<EventItemCategory>,
    val registerUrl: String,
    val about: String,
)

data class EventItemCategory(
    val id: Int,
    val name: String,
)