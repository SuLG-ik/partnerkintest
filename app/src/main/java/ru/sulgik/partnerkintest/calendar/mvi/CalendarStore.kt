package ru.sulgik.partnerkintest.calendar.mvi

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sulgik.partnerkintest.calendar.domain.models.CalendarItemModel
import ru.sulgik.partnerkintest.calendar.domain.usecase.LoadCalendarUseCase

@Immutable
data class CalendarState(
    val content: Content,
) {
    sealed class Content {
        data object Loading : Content()
        data object Error : Content()
        data class Success(
            val items: List<CalendarItemModel>,
        ) : Content()
    }
}


sealed class CalendarViewEvent {
    data object OnStart : CalendarViewEvent()
    data object OnRetry : CalendarViewEvent()
    data object OnBack : CalendarViewEvent()
    data object OnSupport : CalendarViewEvent()
    data class OnItemClicked(val id: Long) : CalendarViewEvent()
}

abstract class CalendarStore : ViewModel() {
    abstract val state: StateFlow<CalendarState>
    abstract fun accept(event: CalendarViewEvent)
}

@Stable
class CalendarStoreImpl(
    private val loadCalendarUseCase: LoadCalendarUseCase,
) : CalendarStore() {

    override val state: MutableStateFlow<CalendarState> = MutableStateFlow(
        CalendarState(content = CalendarState.Content.Loading)
    )

    init {
        accept(CalendarViewEvent.OnStart)
    }

    override fun accept(event: CalendarViewEvent) {
        when (event) {
            CalendarViewEvent.OnStart -> loadCalendar()
            CalendarViewEvent.OnRetry -> loadCalendar()
            else -> {}
        }
    }

    private fun loadCalendar() {
        viewModelScope.launch {
            state.value = CalendarState(content = CalendarState.Content.Loading)

            try {
                val items = loadCalendarUseCase()
                state.value = CalendarState(content = CalendarState.Content.Success(items))
            } catch (e: Exception) {
                state.value = CalendarState(content = CalendarState.Content.Error)
            }
        }
    }

}