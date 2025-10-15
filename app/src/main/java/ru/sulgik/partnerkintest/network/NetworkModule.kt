package ru.sulgik.partnerkintest.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.partnerkintest.BuildConfig
import ru.sulgik.partnerkintest.network.api.CalendarApi
import ru.sulgik.partnerkintest.network.api.EventApi
import ru.sulgik.partnerkintest.network.api.KtorCalendarApi
import ru.sulgik.partnerkintest.network.api.KtorEventApi

val networkModule = module {
    singleOf(::provideHttpClient)

    singleOf(::KtorCalendarApi) bind CalendarApi::class
    singleOf(::KtorEventApi) bind EventApi::class
}

fun provideHttpClient(): HttpClient {
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        if (BuildConfig.DEBUG) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("HttpClient", message)
                    }
                }
                level = LogLevel.ALL
            }
        }
        defaultRequest {
            url("https://partnerkin.com/")
            url.parameters.append("api_key", BuildConfig.API_KEY)
        }
    }
}