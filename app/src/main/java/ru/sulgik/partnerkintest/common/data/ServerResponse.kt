package ru.sulgik.partnerkintest.common.data

import kotlinx.serialization.Serializable

@Serializable
class ServerResponse<T : Any>(
    val error: ServerError?,
    val data: T?,
)

@Serializable
data class ServerError(
    val code: Int,
    val message: String?,
)