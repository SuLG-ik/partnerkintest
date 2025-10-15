package ru.sulgik.partnerkintest.calendar

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import ru.sulgik.partnerkintest.Screens
import ru.sulgik.partnerkintest.calendar.mvi.CalendarStore
import ru.sulgik.partnerkintest.calendar.mvi.CalendarViewEvent
import ru.sulgik.partnerkintest.calendar.ui.CalendarView

fun NavGraphBuilder.calendarScreen(navController: NavController) {
    composable<Screens.Calendar> { entry ->
        val store = koinViewModel<CalendarStore>()
        val state = store.state.collectAsState()
        CalendarView(
            state = state.value,
            onViewEvent = { event ->
                when (event) {
                    is CalendarViewEvent.OnBack -> {
                        navController.popBackStack()
                    }

                    is CalendarViewEvent.OnItemClicked -> {
                        navController.navigate(Screens.CalendarEvent(event.id))
                    }

                    else -> {
                        store.accept(event)
                    }
                }
            },
        )
    }
}