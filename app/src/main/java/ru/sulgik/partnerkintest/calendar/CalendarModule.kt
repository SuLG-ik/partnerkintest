package ru.sulgik.partnerkintest.calendar

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.partnerkintest.calendar.data.repository.CalendarRepositoryImpl
import ru.sulgik.partnerkintest.calendar.domain.repository.CalendarRepository
import ru.sulgik.partnerkintest.calendar.domain.usecase.LoadCalendarUseCase
import ru.sulgik.partnerkintest.calendar.mvi.CalendarStore
import ru.sulgik.partnerkintest.calendar.mvi.CalendarStoreImpl

val calendarModule = module {
    singleOf(::CalendarRepositoryImpl) bind CalendarRepository::class

    singleOf(::LoadCalendarUseCase)

    viewModelOf(::CalendarStoreImpl) bind CalendarStore::class
}