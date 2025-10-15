package ru.sulgik.partnerkintest.event.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import ru.sulgik.partnerkintest.event.mvi.EventState
import ru.sulgik.partnerkintest.event.mvi.EventViewEvent
import ru.sulgik.partnerkintest.ui.theme.UIKitTheme
import ru.sulgik.partnerkintest.uikit.loader.UIKitLoader
import ru.sulgik.partnerkintest.uikit.tag.UIKitTag
import ru.sulgik.partnerkintest.uikit.tag.UIKitTagStyle
import ru.sulgik.partnerkintest.utils.formattedShortMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventView(
    state: EventState,
    onViewEvent: (EventViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onViewEvent(EventViewEvent.OnBack) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_icon_go_back),
                            contentDescription = null,
                            tint = UIKitTheme.colorScheme.blackText,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = UIKitTheme.colorScheme.white,
                ),
            )
        },
        containerColor = UIKitTheme.colorScheme.white,
        contentColor = UIKitTheme.colorScheme.blackText,
        modifier = modifier,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (state.content) {
                EventState.Content.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = stringResource(R.string.unknown_error))
                        OutlinedButton(onClick = { onViewEvent(EventViewEvent.OnRetry) }) {
                            Text(text = stringResource(R.string.retry))
                        }
                    }
                }

                EventState.Content.Loading -> {
                    UIKitLoader(modifier = Modifier.fillMaxSize())
                }

                is EventState.Content.Success -> {
                    EventSuccessContent(
                        event = state.content.items,
                        onViewEvent = onViewEvent,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun EventSuccessContent(
    event: ru.sulgik.partnerkintest.event.domain.models.EventModel,
    onViewEvent: (EventViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(UIKitTheme.dimensions.spacing16),
    ) {
        Text(
            text = event.type,
            fontSize = 14.sp,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing4))
        Text(
            text = event.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing20))

        AsyncImage(
            model = event.imageUrl,
            contentDescription = event.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = UIKitTheme.dimensions.spacing16)
                .clip(UIKitTheme.rounding.rounding12)
        )

        Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing20))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            event.categories.forEach { category ->
                UIKitTag(
                    text = category.name,
                    style = UIKitTagStyle.defaultGray()
                )
            }
        }

        Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing20))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = null,
                    tint = UIKitTheme.colorScheme.buttonArtConf,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "${event.startDate.dayOfMonth} ${event.startDate.formattedShortMonth} ${event.startDate.year}, ${event.endDate.dayOfMonth - event.startDate.dayOfMonth + 1} дня",
                    fontSize = 14.sp,
                    color = UIKitTheme.colorScheme.blackText,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null,
                    tint = UIKitTheme.colorScheme.buttonArtConf,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "${event.city}, ${event.country}",
                    fontSize = 14.sp,
                    color = UIKitTheme.colorScheme.blackText,
                )
            }
        }

        Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing28))

        Button(
            onClick = { onViewEvent(EventViewEvent.OnRegistration) },
            colors = ButtonDefaults.buttonColors(
                containerColor = UIKitTheme.colorScheme.buttonArtConf,
                contentColor = UIKitTheme.colorScheme.white,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = UIKitTheme.rounding.rounding12
        ) {
            Text(
                text = stringResource(R.string.event_registration),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing28))

        Text(
            text = stringResource(R.string.event_about_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = UIKitTheme.colorScheme.blackText,
        )

        Spacer(modifier = Modifier.height(UIKitTheme.dimensions.spacing12))

        Text(
            text = event.about,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = UIKitTheme.colorScheme.blackText,
        )
    }
}