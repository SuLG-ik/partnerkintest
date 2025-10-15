package ru.sulgik.partnerkintest.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.sulgik.partnerkintest.R
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemLocation
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemModel
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemStatus
import ru.sulgik.partnerkintest.ui.theme.UIKitTheme
import ru.sulgik.partnerkintest.uikit.tag.UIKitTag
import ru.sulgik.partnerkintest.uikit.tag.UIKitTagStyle
import ru.sulgik.partnerkintest.utils.formattedShortMonth

@Composable
fun CalendarConferenceItemView(
    item: CalendarItemModel.Conference,
    onClick: (CalendarItemModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = item.status.backgroundColor,
        shape = UIKitTheme.rounding.rounding16,
        onClick = { onClick(item) },
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = UIKitTheme.dimensions.spacing16),
        ) {
            if (item.status is CalendarItemStatus.Canceled) {
                Spacer(modifier = Modifier.height(12.dp))
                UIKitTag(
                    text = item.status.value,
                    leadingIcon = painterResource(R.drawable.ic_lightning_bold),
                    style = UIKitTagStyle.defaultError(),
                )
                Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing26))
            } else {
                Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing24))
            }

            Text(
                text = item.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = UIKitTheme.colorScheme.blackText,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(104.dp)
                    .clip(UIKitTheme.rounding.rounding12)
                    .background(item.status.backgroundColor),
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5f),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    DateBox(
                        day = item.startDate.dayOfMonth.toString().padStart(2, '0'),
                        month = item.startDate.formattedShortMonth
                    )

                    Text(
                        text = "—",
                        fontSize = 24.sp,
                        color = UIKitTheme.colorScheme.blackText
                    )

                    DateBox(
                        day = item.endDate.dayOfMonth.toString().padStart(2, '0'),
                        month = item.endDate.formattedShortMonth
                    )
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item.categories.forEach { category ->
                    CategoryChip(name = category.name)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null,
                    tint = UIKitTheme.colorScheme.blackText,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = item.locationText,
                    fontSize = 14.sp,
                    color = UIKitTheme.colorScheme.blackText,
                )
            }
            Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing24))
        }
    }
}

@Composable
private fun DateBox(
    day: String,
    month: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = day,
            fontSize = 40.sp,
            lineHeight = 56.sp,
            color = UIKitTheme.colorScheme.blackText,
        )
        Text(
            text = month,
            fontSize = 12.sp,
            color = UIKitTheme.colorScheme.blackText.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun CategoryChip(
    name: String,
    modifier: Modifier = Modifier
) {
    UIKitTag(
        text = name,
        modifier = modifier,
        style = UIKitTagStyle.defaultWhite(),
    )
}

private val CalendarItemModel.Conference.locationText: String
    @Composable
    get() = when (location) {
        is CalendarItemLocation.Online -> stringResource(R.string.online)
        is CalendarItemLocation.Offline -> "${this.location.country}, ${this.location.city}"
        null -> "Unknown"
    }

private val CalendarItemStatus.backgroundColor
    @Composable get() = when (this) {
        is CalendarItemStatus.Canceled -> UIKitTheme.colorScheme.red.copy(alpha = 0.1f)
        is CalendarItemStatus.Published -> UIKitTheme.colorScheme.buttonArtConf.copy(alpha = 0.1f)
    }