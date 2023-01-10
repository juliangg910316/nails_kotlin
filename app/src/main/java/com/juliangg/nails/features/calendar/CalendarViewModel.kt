package com.juliangg.nails.features.calendar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import com.juliangg.nails.database.turn.Turn
import com.juliangg.nails.database.turn.TurnDao
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import java.time.LocalDateTime

class CalendarViewModel(
    private val turnDao: TurnDao
): ViewModel() {
    val kalendarDay: KalendarDay = KalendarDay(LocalDate(
        LocalDateTime.now().year,
        LocalDateTime.now().monthValue,
        LocalDateTime.now().dayOfMonth),
    )
    val kalendarEvent = listOf(
        KalendarEvent(LocalDate(2022, 12, 25), "Julian","9 AM"),
        KalendarEvent(LocalDate(2022, 12, 25), "Beidis","11 AM"),
        KalendarEvent(LocalDate(2022, 12, 25), "Maribel","2 PM"),
        KalendarEvent(LocalDate(2022, 12, 25), "Julian","5 AM"),
        KalendarEvent(LocalDate(2022, 12, 28), "Beidis","9 AM"),
        KalendarEvent(LocalDate(2022, 12, 29), "Maribel","9 AM"),
    )
    var calendarDayEventState = mutableStateOf(CalendarDayEvent(kalendarDay = kalendarDay, kalendarEventList = kalendarEvent))

    fun fullTurn(): Flow<List<Turn>> = turnDao.getAll()

    fun saveTurn(turn: Turn) {
        turnDao.saveAll(turn)
    }

    fun addCalendarDayEvent(calendarDayEvent: CalendarDayEvent) {
        calendarDayEventState.value = calendarDayEvent
    }

    fun doSomething() {

    }

    fun doSomethingMore() {

    }

}