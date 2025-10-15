package ru.sulgik.partnerkintest.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

internal val LocalUIKitRounding = staticCompositionLocalOf { UIKitRounding() }

@Immutable
data class UIKitRounding(
    val rounding12: RoundedCornerShape = RoundedCornerShape(corner = CornerSize(12.dp)),
    val rounding16: RoundedCornerShape = RoundedCornerShape(corner = CornerSize(16.dp)),
    val roundingFull: RoundedCornerShape = CircleShape,
)