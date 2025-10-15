package ru.sulgik.partnerkintest.event.mvi

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sulgik.partnerkintest.event.domain.models.EventModel
import ru.sulgik.partnerkintest.event.domain.usecase.LoadEventUseCase

@Immutable
data class EventState(
    val content: Content,
) {
    sealed class Content {
        data object Loading : Content()
        data object Error : Content()
        data class Success(
            val items: EventModel,
        ) : Content()
    }
}


sealed class EventViewEvent {
    data object OnStart : EventViewEvent()
    data object OnRetry : EventViewEvent()
    data object OnBack : EventViewEvent()
    data object OnRegistration : EventViewEvent()
}

abstract class EventStore : ViewModel() {
    abstract val state: StateFlow<EventState>
    abstract fun accept(event: EventViewEvent)
}

@Stable
class EventStoreImpl(
    private val eventId: Long,
    private val loadEventUseCase: LoadEventUseCase,
) : EventStore() {

    override val state: MutableStateFlow<EventState> = MutableStateFlow(
        EventState(content = EventState.Content.Loading)
    )

    init {
        accept(EventViewEvent.OnStart)
    }

    override fun accept(event: EventViewEvent) {
        when (event) {
            EventViewEvent.OnStart -> loadEvents()
            EventViewEvent.OnRetry -> loadEvents()
            else -> {}
        }
    }

    private fun loadEvents() {
        viewModelScope.launch {
            state.value = EventState(content = EventState.Content.Loading)

            try {
                val eventModel = loadEventUseCase(id = eventId)
                state.value = EventState(content = EventState.Content.Success(eventModel))
            } catch (e: Exception) {
                state.value = EventState(content = EventState.Content.Error)
            }
        }
    }

}