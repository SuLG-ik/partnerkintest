package ru.sulgik.partnerkintest.event

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.partnerkintest.event.data.repository.EventRepositoryImpl
import ru.sulgik.partnerkintest.event.domain.repository.EventRepository
import ru.sulgik.partnerkintest.event.domain.usecase.LoadEventUseCase
import ru.sulgik.partnerkintest.event.mvi.EventStore
import ru.sulgik.partnerkintest.event.mvi.EventStoreImpl

val eventModule = module {
    singleOf(::EventRepositoryImpl) bind EventRepository::class

    singleOf(::LoadEventUseCase)

    viewModelOf(::EventStoreImpl) bind EventStore::class
}