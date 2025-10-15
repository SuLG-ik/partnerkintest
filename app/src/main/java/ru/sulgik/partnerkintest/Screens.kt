package ru.sulgik.partnerkintest

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object Calendar : Screens()
    @Serializable
    data class CalendarEvent(val id: Long) : Screens()
}