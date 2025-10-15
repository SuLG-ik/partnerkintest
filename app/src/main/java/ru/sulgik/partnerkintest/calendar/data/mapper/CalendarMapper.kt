package ru.sulgik.partnerkintest.calendar.data.mapper

import kotlinx.collections.immutable.toImmutableList
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemCategory
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemModel
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemStatus
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemLocation
import ru.sulgik.partnerkintest.network.entity.CalendarData
import java.time.LocalDate

fun CalendarMapper(
    data: CalendarData
): List<CalendarItemModel> {
    return data.result.map { item ->
        val conference = item.conference
        CalendarItemModel.Conference(
            id = conference.id,
            title = conference.name,
            startDate = LocalDate.parse(conference.startDate),
            endDate = LocalDate.parse(conference.endDate),
            location = when(conference.format) {
                "online" -> CalendarItemLocation.Online
                "offline" -> CalendarItemLocation.Offline(
                    city = conference.city,
                    country = conference.country,
                )
                else -> null
            },
            imageUrl = conference.image.url,
            categories = conference.categories.map { category ->
                CalendarItemCategory(
                    id = category.id,
                    name = category.name,
                )
            }.toImmutableList(),
            status = when (conference.status) {
                "canceled" -> CalendarItemStatus.Canceled(conference.statusTitle)
                "publish" -> CalendarItemStatus.Published(conference.statusTitle)
                else -> CalendarItemStatus.Published(conference.statusTitle)
            },
        )
    }
}