package ru.sulgik.partnerkintest.calendar.domain.models

import kotlinx.collections.immutable.ImmutableList
import java.time.LocalDate

sealed class CalendarItemModel {
    abstract val id: Long
    abstract val startDate: LocalDate
    abstract val endDate: LocalDate

    data class Conference(
        override val id: Long,
        val title: String,
        override val startDate: LocalDate,
        override val endDate: LocalDate,
        val imageUrl: String,
        val categories: ImmutableList<CalendarItemCategory>,
        val status: CalendarItemStatus,
        val location: CalendarItemLocation?,
    ) : CalendarItemModel()
}

sealed class CalendarItemLocation {
    data object Online: CalendarItemLocation()
    data class Offline(val city: String, val country: String): CalendarItemLocation()
}

data class CalendarItemCategory(
    val id: Int,
    val name: String,
)

sealed class CalendarItemStatus {
    abstract val value: String

    data class Canceled(override val value: String) : CalendarItemStatus()
    data class Published(override val value: String) : CalendarItemStatus()
}