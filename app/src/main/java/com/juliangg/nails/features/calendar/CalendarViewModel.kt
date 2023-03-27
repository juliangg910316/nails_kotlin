package com.juliangg.nails.features.calendar

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import com.juliangg.nails.database.turn.Turn
import com.juliangg.nails.database.turn.TurnDao
import com.juliangg.nails.database.turn.TurnRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val turnRepository: TurnRepository
) : ViewModel() {

    val allTurns: Flow<List<Turn>> = turnRepository.getAll()

    val daySelected: MutableLiveData<LocalDate> by lazy {
        MutableLiveData<LocalDate>()
    }

    val turnSelected: MutableLiveData<Turn> by lazy {
        MutableLiveData<Turn>()
    }

    var turnsDay: Flow<List<Turn>> =
        turnRepository.getTurnsFromDate(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        )

    val kalendarEvent = listOf(
        KalendarEvent(LocalDate(2023, 2, 25), "Julian", "9 AM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Beidis", "11 AM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Maribel", "2 PM"),
        KalendarEvent(LocalDate(2023, 2, 25), "Julian", "5 AM"),
        KalendarEvent(LocalDate(2023, 2, 26), "Beidis", "9 AM"),
        KalendarEvent(LocalDate(2023, 2, 27), "Maribel", "9 AM"),
    )

    fun getKalendarEvent(turns : List<Turn>) : List<KalendarEvent> {
        Log.i("TAG", "getKalendarEvent: $turns")
        val events : MutableList<KalendarEvent> = mutableListOf()
        turns.forEach { turn -> events.add(KalendarEvent(LocalDate.parse(turn.day), turn.nameClient, turn.hour)) }
        return events
    }
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

    fun saveTurn() {
        Log.i("TAG", "saveTurn: " + turnSelected.value)
        viewModelScope.launch {
            turnSelected.value?.let { turnRepository.saveAll(it) }
        }

    }

    fun setDaySelected(kalendarDay: KalendarDay) {
        Log.i("TAG", "setDaySelected: ${kalendarDay.localDate}")
        daySelected.value = kalendarDay.localDate
        turnSelected.value?.day = daySelected.value.toString()
        turnsDay = turnRepository.getTurnsFromDate(daySelected.value.toString())
    }

    fun setTurnSelected(turn: Turn) {
        Log.i("TAG", "setTurnSelected: $turn")
        turnSelected.value = turn
    }

    fun setHour(hour: String) {
        Log.i("TAG", "setHour: $hour")
        turnSelected.value?.hour = hour
    }

    fun setName(name: String) {
        Log.i("TAG", "setName: $name")
        turnSelected.value?.nameClient = name
    }

    fun setPhone(phone: String) {
        Log.i("TAG", "setPhone: $phone")
        turnSelected.value?.phoneClient = phone
    }

    fun setPayPrevious(pay: Int) {
        Log.i("TAG", "setPayPrevious: $pay")
        turnSelected.value?.payPrevious = pay
    }

    fun setPayTotal(pay: Int) {
        Log.i("TAG", "setPayTotal: $pay")
        turnSelected.value?.payTotal = pay
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