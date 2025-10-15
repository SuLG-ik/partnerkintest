package ru.sulgik.partnerkintest.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object UIKitTheme {
    val colorScheme: UIKitColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalUIKitColorScheme.current

    val dimensions: UIKitDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalUIKitDimensions.current

    val rounding: UIKitRounding
        @Composable
        @ReadOnlyComposable
        get() = LocalUIKitRounding.current
}