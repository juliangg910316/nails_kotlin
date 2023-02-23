package com.juliangg.nails.features.calendar

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val turnRepository: TurnRepository
) : ViewModel() {

    val daySelected: MutableLiveData<LocalDate> by lazy {
        MutableLiveData<LocalDate>()
    }

    val turnSelected: MutableLiveData<Turn> by lazy {
        MutableLiveData<Turn>()
    }

    val turnsDay: Flow<List<Turn>> =
        turnRepository.getTurnsFromDate(
            "${daySelected.value?.dayOfMonth}-${daySelected.value?.monthNumber}-${daySelected.value?.year}"
        )

    val kalendarEvent = listOf(
        KalendarEvent(LocalDate(2023, 2, 25), "Julian", "9 AM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Beidis", "11 AM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Maribel", "2 PM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Julian", "5 AM"),
        KalendarEvent(LocalDate(2023, 2, 26), "Beidis", "9 AM"),
        KalendarEvent(LocalDate(2023, 2, 27), "Maribel", "9 AM"),
    )
    init {
        daySelected.value = LocalDate(
            LocalDateTime.now().year,
            LocalDateTime.now().monthValue,
            LocalDateTime.now().dayOfMonth
        )
        turnSelected.value = Turn(
            id = UUID.randomUUID().toString(),
            day = daySelected.value.toString()
        )
    }

    fun fullTurn(): Flow<List<Turn>> = turnRepository.getAll()

    fun saveTurn() {
        turnSelected.value?.let { turnRepository.saveAll(it) }
    }

    fun setDaySelected(kalendarDay: KalendarDay) {
        Log.i("TAG", "setDaySelected: ${kalendarDay.localDate}")
        daySelected.value = kalendarDay.localDate
        turnSelected.value?.day = daySelected.value.toString()
    }

    fun setTurnSelected(turn: Turn) {
        Log.i("TAG", "setTurnSelected: $turn")
        turnSelected.value = turn
    }

    fun setHour(hour: String) {
        turnSelected.value?.hour = hour
    }

    fun setAm(am: Boolean) {
        turnSelected.value?.am = am
    }

    fun setName(name: String) {
        turnSelected.value?.nameClient = name
    }

    fun setPhone(phone: String) {
        turnSelected.value?.phoneClient = phone
    }

    /*fun getDaySelected() : String{
        Log.i("TAG", "getDaySelected: ${daySelected.value}")
        return daySelected.value.toString();
    }*/

    fun doSomething() {

    }

    fun doSomethingMore() {

    }


}