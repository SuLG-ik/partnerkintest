package ru.sulgik.partnerkintest.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarData(
    val counts: Int,
    val pagination: Pagination,
    val result: List<CalendarItem>,
)

@Serializable
data class Pagination(
    @SerialName("page_size")
    val pageSize: Int,
    @SerialName("current_page")
    val currentPage: Int,
)

@Serializable
data class CalendarItem(
    @SerialName("view_type")
    val viewType: Int,
    val conference: Conference,
)

@Serializable
data class Conference(
    val id: Long,
    val name: String,
    val format: String,
    val status: String,
    @SerialName("status_title")
    val statusTitle: String,
    val url: String,
    val image: Image,
    val rating: String? = null,
    @SerialName("start_date")
    val startDate: String,
    @SerialName("end_date")
    val endDate: String,
    val oneday: Int,
    @SerialName("custom_date")
    val customDate: String? = null,
    @SerialName("country_id")
    val countryId: Int,
    val country: String,
    @SerialName("city_id")
    val cityId: Int,
    val city: String,
    val categories: List<Category>,
    @SerialName("type_id")
    val typeId: Int,
    val type: ConferenceType,
)

@Serializable
data class Image(
    val id: String,
    val url: String,
    val preview: String,
    @SerialName("placeholder_color")
    val placeholderColor: String? = null,
    val width: Int,
    val height: Int,
)

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val url: String,
)

@Serializable
data class ConferenceType(
    val id: Int,
    val name: String,
)