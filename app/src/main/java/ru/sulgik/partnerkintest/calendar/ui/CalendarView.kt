package ru.sulgik.partnerkintest.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.sulgik.partnerkintest.R
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemModel
import ru.sulgik.partnerkintest.calendar.mvi.CalendarState
import ru.sulgik.partnerkintest.calendar.mvi.CalendarViewEvent
import ru.sulgik.partnerkintest.ui.theme.UIKitTheme
import ru.sulgik.partnerkintest.uikit.loader.UIKitLoader
import ru.sulgik.partnerkintest.utils.formattedMonth

@Composable
fun CalendarView(
    state: CalendarState,
    onViewEvent: (CalendarViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CalendarTopBar(
                onBack = { onViewEvent(CalendarViewEvent.OnBack) },
                onSupport = { onViewEvent(CalendarViewEvent.OnSupport) },
            )
        },
        containerColor = UIKitTheme.colorScheme.white,
        contentColor = UIKitTheme.colorScheme.blackText,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state.content) {
                CalendarState.Content.Error -> {
                    ErrorCalendarView(
                        onRetry = { onViewEvent(CalendarViewEvent.OnRetry) },
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                CalendarState.Content.Loading -> {
                    UIKitLoader(
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                is CalendarState.Content.Success -> {
                    SuccessCalendarView(
                        items = state.content.items,
                        onClick = { onViewEvent(CalendarViewEvent.OnItemClicked(it.id)) },
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorCalendarView(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(stringResource(R.string.unknown_error))
        OutlinedButton(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
private fun SuccessCalendarView(
    items: List<CalendarItemModel>,
    onClick: (CalendarItemModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitTheme.dimensions.spacing12)
    ) {
        items(
            count = items.size,
            key = { items[it].id },
            contentType = {
                if (itemHasHeadingIn(it, items)) {
                    "item_with_heading"
                } else {
                    "item"
                }
            },
        ) { index ->
            val item = items[index]
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (itemHasHeadingIn(index, items)) {
                    Text(
                        text = item.heading,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = UIKitTheme.colorScheme.blackText,
                        modifier = Modifier
                            .padding(
                                horizontal = UIKitTheme.dimensions.spacing16,
                                vertical = UIKitTheme.dimensions.spacing24,
                            ),
                    )
                }

                CalendarItemView(
                    item = item,
                    onClick = onClick,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun CalendarItemView(
    item: CalendarItemModel,
    onClick: (CalendarItemModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (item) {
        is CalendarItemModel.Conference -> {
            CalendarConferenceItemView(
                item = item,
                onClick = onClick,
                modifier = modifier.fillMaxWidth(),
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarTopBar(
    onBack: () -> Unit,
    onSupport: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.calendar_screen_title),
                color = UIKitTheme.colorScheme.blackText,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_go_back),
                    contentDescription = null,
                    tint = UIKitTheme.colorScheme.blackText,
                )
            }
        },
        actions = {
            IconButton(onClick = onSupport) {
                Icon(
                    painter = painterResource(R.drawable.ic_hugeicons_customer_support),
                    contentDescription = null,
                    tint = UIKitTheme.colorScheme.blackText,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = UIKitTheme.colorScheme.white,
        ),
        modifier = modifier,
    )
}


private fun itemHasHeadingIn(index: Int, items: List<CalendarItemModel>): Boolean {
    if (index == 0) {
        return true
    }
    val current = items[index]
    val previous = items[index - 1]
    if (previous.startDate.year != current.startDate.year) {
        return true
    }
    if (previous.startDate.monthValue != current.startDate.monthValue) {
        return true
    }
    return false
}

private val CalendarItemModel.heading: String
    @Composable
    get() {
        return "${startDate.formattedMonth}, ${startDate.year}"
    }