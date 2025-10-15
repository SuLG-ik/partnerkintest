package ru.sulgik.partnerkintest.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventData(
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
    @SerialName("register_url")
    val registerUrl: String,
    val about: String,
)
