package ru.sulgik.partnerkintest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.sulgik.partnerkintest.calendar.calendarModule
import ru.sulgik.partnerkintest.event.eventModule
import ru.sulgik.partnerkintest.network.networkModule

class PartnerkinTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PartnerkinTestApplication)
            modules(
                networkModule,
                calendarModule,
                eventModule,
            )
        }
    }
}