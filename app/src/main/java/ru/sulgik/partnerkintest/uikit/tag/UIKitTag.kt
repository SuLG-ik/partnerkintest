package ru.sulgik.partnerkintest.uikit.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sulgik.partnerkintest.ui.theme.UIKitTheme
import ru.sulgik.partnerkintest.utils.thenIfNotNull

data class UIKitTagColors(
    val textColor: Color,
    val iconColor: Color,
    val backgroundColor: Color,
    val borderColor: Color?,
) {
    companion object {
        @Composable
        fun white(): UIKitTagColors {
            return UIKitTagColors(
                textColor = UIKitTheme.colorScheme.blackText,
                iconColor = UIKitTheme.colorScheme.blackText,
                backgroundColor = UIKitTheme.colorScheme.white,
                borderColor = null
            )
        }
        @Composable
        fun gray(): UIKitTagColors {
            return UIKitTagColors(
                textColor = UIKitTheme.colorScheme.blackText,
                iconColor = UIKitTheme.colorScheme.blackText,
                backgroundColor = UIKitTheme.colorScheme.grayBg,
                borderColor = null
            )
        }

        @Composable
        fun error(): UIKitTagColors {
            return UIKitTagColors(
                textColor = UIKitTheme.colorScheme.red,
                iconColor = UIKitTheme.colorScheme.red,
                backgroundColor = UIKitTheme.colorScheme.transparent,
                borderColor = UIKitTheme.colorScheme.red,
            )
        }
    }
}


enum class TagSizeTypes(
    val padding: PaddingValues,
    val textStyle: @Composable () -> TextStyle,
    val iconSize: Dp,
    val rounding: @Composable () -> RoundedCornerShape,
) {
    DEFAULT(
        padding = PaddingValues(horizontal = 10.dp, vertical = 4.5.dp),
        textStyle = {
            LocalTextStyle.current.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                lineHeight = 15.sp,
            )
        },
        iconSize = 12.dp,
        rounding = { UIKitTheme.rounding.roundingFull },
    )
}

data class UIKitTagStyle(
    val types: TagSizeTypes,
    val colors: UIKitTagColors,
) {
    companion object {
        @Composable
        fun defaultWhite() = UIKitTagStyle(
            types = TagSizeTypes.DEFAULT,
            colors = UIKitTagColors.white(),
        )

        @Composable
        fun defaultGray() = UIKitTagStyle(
            types = TagSizeTypes.DEFAULT,
            colors = UIKitTagColors.gray(),
        )

        @Composable
        fun defaultError() = UIKitTagStyle(
            types = TagSizeTypes.DEFAULT,
            colors = UIKitTagColors.error(),
        )
    }
}

@Composable
fun UIKitTag(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    onClick: (() -> Unit)? = null,
    style: UIKitTagStyle = UIKitTagStyle.defaultWhite(),
) {
    Row(
        modifier = modifier
            .background(
                color = style.colors.backgroundColor,
                shape = style.types.rounding(),
            )
            .width(IntrinsicSize.Max)
            .clip(style.types.rounding())
            .thenIfNotNull(style.colors.borderColor) {
                Modifier.border(
                    width = 1.dp,
                    color = it,
                    shape = style.types.rounding(),
                )
            }
            .thenIfNotNull(onClick) {
                Modifier.clickable(onClick = it)
            }
            .padding(style.types.padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) {
            Icon(
                painter = leadingIcon,
                contentDescription = null,
                tint = style.colors.iconColor,
                modifier = Modifier.size(style.types.iconSize)
            )
            Spacer(modifier = Modifier.width(UIKitTheme.dimensions.spacing2))
        }
        Text(
            text = text,
            style = style.types.textStyle(),
            color = style.colors.textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
        )
        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(UIKitTheme.dimensions.spacing2))
            Icon(
                painter = trailingIcon,
                contentDescription = null,
                tint = style.colors.iconColor,
                modifier = Modifier.size(style.types.iconSize)
            )
        }
    }
}