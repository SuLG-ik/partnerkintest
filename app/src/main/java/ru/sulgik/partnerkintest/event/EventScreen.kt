package ru.sulgik.partnerkintest.event

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.sulgik.partnerkintest.Screens
import ru.sulgik.partnerkintest.event.mvi.EventStore
import ru.sulgik.partnerkintest.event.mvi.EventViewEvent
import ru.sulgik.partnerkintest.event.ui.EventView

fun NavGraphBuilder.eventScreen(navController: NavController) {
    composable<Screens.CalendarEvent> { entry ->
        val route = entry.toRoute<Screens.CalendarEvent>()
        val store = koinViewModel<EventStore>(parameters = { parametersOf(route.id) })
        val state = store.state.collectAsState()
        EventView(
            state = state.value,
            onViewEvent = { event ->
                when (event) {
                    is EventViewEvent.OnBack -> {
                        navController.popBackStack()
                    }

                    else -> {
                        store.accept(event)
                    }
                }
            },
        )
    }
}