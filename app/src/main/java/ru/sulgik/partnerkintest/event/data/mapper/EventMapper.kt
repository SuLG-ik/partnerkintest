package ru.sulgik.partnerkintest.event.data.mapper

import kotlinx.collections.immutable.toImmutableList
import ru.sulgik.partnerkintest.event.domain.models.EventItemCategory
import ru.sulgik.partnerkintest.event.domain.models.EventModel
import ru.sulgik.partnerkintest.network.entity.CalendarData
import ru.sulgik.partnerkintest.network.entity.EventData
import java.time.LocalDate


fun EventMapper(
    data: EventData
): EventModel {
    return EventModel(
        id = data.id,
        name = data.name,
        type = data.type.name,
        imageUrl = data.image.url,
        imagePreviewUrl = data.image.preview,
        startDate = LocalDate.parse(data.startDate),
        endDate = LocalDate.parse(data.endDate),
        city = data.city,
        country = data.country,
        categories = data.categories.map { category ->
            EventItemCategory(
                id = category.id,
                name = category.name,
            )
        }.toImmutableList(),
        registerUrl = data.registerUrl,
        about = data.about,
    )
}