package ru.sulgik.partnerkintest.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalUIKitColorScheme = staticCompositionLocalOf { uiKitLight() }

@Stable
class UIKitColorScheme(
    isLight: Boolean,
    transparent: Color,
    blackText: Color,
    grayBg: Color,
    buttonArtConf: Color,
    buttonConf: Color,
    red: Color,
    white: Color,
) {
    var isLight by mutableStateOf(isLight)
        private set
    var transparent by mutableStateOf(transparent)
        private set
    var blackText by mutableStateOf(blackText)
        private set
    var grayBg by mutableStateOf(grayBg)
        private set
    var buttonArtConf by mutableStateOf(buttonArtConf)
        private set
    var buttonConf by mutableStateOf(buttonConf)
        private set
    var red by mutableStateOf(red)
        private set
    var white by mutableStateOf(white)
        private set

    override fun toString() = "UIKitColorScheme(" +
            "isLight=$isLight, " +
            "transparent=$transparent, " +
            "blackText=$blackText, " +
            "grayBg=$grayBg, " +
            "buttonArtConf=$buttonArtConf, " +
            "buttonConf=$buttonConf, " +
            "red=$red, " +
            "white=$white" +
            ")"

    fun update(colors: UIKitColorScheme) {
        isLight = colors.isLight
        transparent = colors.transparent
        blackText = colors.blackText
        grayBg = colors.grayBg
        buttonArtConf = colors.buttonArtConf
        buttonConf = colors.buttonConf
        red = colors.red
        white = colors.white
    }

    fun copy() = UIKitColorScheme(
        isLight = isLight,
        transparent = transparent,
        blackText = blackText,
        grayBg = grayBg,
        buttonArtConf = buttonArtConf,
        buttonConf = buttonConf,
        red = red,
        white = white,
    )
}

fun uiKitLight() = UIKitColorScheme(
    isLight = true,
    transparent = Palette.Light.transparent,
    blackText = Palette.Dark.blackText,
    grayBg = Palette.Dark.grayBg,
    buttonArtConf = Palette.Dark.buttonArtConf,
    buttonConf = Palette.Dark.buttonConf,
    red = Palette.Dark.red,
    white = Palette.Dark.white,
)

fun uiKitDark() = UIKitColorScheme(
    isLight = false,
    transparent = Palette.Dark.transparent,
    blackText = Palette.Light.blackText,
    grayBg = Palette.Light.grayBg,
    buttonArtConf = Palette.Light.buttonArtConf,
    buttonConf = Palette.Light.buttonConf,
    red = Palette.Light.red,
    white = Palette.Light.white,
)
