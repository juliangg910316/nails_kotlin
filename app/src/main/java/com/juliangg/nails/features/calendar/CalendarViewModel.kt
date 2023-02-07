package com.juliangg.nails.features.calendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import com.juliangg.nails.database.turn.Turn
import com.juliangg.nails.database.turn.TurnDao
import com.juliangg.nails.database.turn.TurnRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val turnRepository: TurnRepository
) : ViewModel() {
    private var daySelected: MutableState<LocalDate> = mutableStateOf(
        LocalDate(
            LocalDateTime.now().year,
            LocalDateTime.now().monthValue,
            LocalDateTime.now().dayOfMonth
        )
    )
    val turnsDay: Flow<List<Turn>> =
        turnRepository.getTurnsFromDate(
            "${daySelected.value.dayOfMonth}-${daySelected.value.monthNumber}-${daySelected.value.year}"
        )

    val kalendarEvent = listOf(
        KalendarEvent(LocalDate(2023, 2, 25), "Julian", "9 AM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Beidis", "11 AM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Maribel", "2 PM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Julian", "5 AM"),
        KalendarEvent(LocalDate(2023, 2, 26), "Beidis", "9 AM"),
        KalendarEvent(LocalDate(2023, 2, 27), "Maribel", "9 AM"),
    )


    fun fullTurn(): Flow<List<Turn>> = turnRepository.getAll()

    fun saveTurn(turn: Turn) {
        turnRepository.saveAll(turn)
    }

    fun setDaySelected(kalendarDay: KalendarDay) {
        daySelected.value = kalendarDay.localDate
    }

    fun doSomething() {

    }

    fun doSomethingMore() {

    }


}