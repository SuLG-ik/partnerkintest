package ru.sulgik.partnerkintest.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.sulgik.partnerkintest.R
import java.time.LocalDate
import java.time.Month.APRIL
import java.time.Month.AUGUST
import java.time.Month.DECEMBER
import java.time.Month.FEBRUARY
import java.time.Month.JANUARY
import java.time.Month.JULY
import java.time.Month.JUNE
import java.time.Month.MARCH
import java.time.Month.MAY
import java.time.Month.NOVEMBER
import java.time.Month.OCTOBER
import java.time.Month.SEPTEMBER

val LocalDate.formattedMonth: String
    @Composable
    get() {
        return when (month) {
            JANUARY -> stringResource(R.string.month_january)
            FEBRUARY -> stringResource(R.string.month_february)
            MARCH -> stringResource(R.string.month_march)
            APRIL -> stringResource(R.string.month_april)
            MAY -> stringResource(R.string.month_may)
            JUNE -> stringResource(R.string.month_june)
            JULY -> stringResource(R.string.month_july)
            AUGUST -> stringResource(R.string.month_august)
            SEPTEMBER -> stringResource(R.string.month_september)
            OCTOBER -> stringResource(R.string.month_october)
            NOVEMBER -> stringResource(R.string.month_november)
            DECEMBER -> stringResource(R.string.month_december)
        }
    }

val LocalDate.formattedShortMonth: String
    @Composable
    get() {
        return when (month) {
            JANUARY -> stringResource(R.string.short_month_january)
            FEBRUARY -> stringResource(R.string.short_month_february)
            MARCH -> stringResource(R.string.short_month_march)
            APRIL -> stringResource(R.string.short_month_april)
            MAY -> stringResource(R.string.short_month_may)
            JUNE -> stringResource(R.string.short_month_june)
            JULY -> stringResource(R.string.short_month_july)
            AUGUST -> stringResource(R.string.short_month_august)
            SEPTEMBER -> stringResource(R.string.short_month_september)
            OCTOBER -> stringResource(R.string.short_month_october)
            NOVEMBER -> stringResource(R.string.short_month_november)
            DECEMBER -> stringResource(R.string.short_month_december)
        }
    }