package ru.sulgik.partnerkintest.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalUIKitDimensions = staticCompositionLocalOf { UIKitDimensions() }

@Immutable
data class UIKitDimensions(
    val spacing2: Dp = 2.dp,
    val spacing4: Dp = 4.dp,
    val spacing8: Dp = 8.dp,
    val spacing10: Dp = 10.dp,
    val spacing12: Dp = 12.dp,
    val spacing16: Dp = 16.dp,
    val spacing20: Dp = 20.dp,
    val spacing24: Dp = 24.dp,
    val spacing26: Dp = 26.dp,
    val spacing28: Dp = 28.dp,
    val spacing32: Dp = 32.dp,
    val spacing36: Dp = 36.dp,
)