package com.juliangg.nails.features.calendar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import com.juliangg.nails.database.turn.Turn
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

    //private val _turnsDay = MutableLiveData<List<Turn>>()
    val turnsDay: MutableLiveData<List<Turn>> by lazy {
        MutableLiveData<List<Turn>>()
    }

    fun getKalendarEvent(turns : List<Turn>) : List<KalendarEvent> {
        Log.i("TAG", "getKalendarEvent: $turns")
        val events : MutableList<KalendarEvent> = mutableListOf()
        turns.forEach { turn -> events.add(KalendarEvent(LocalDate.parse(turn.day), turn.nameClient, turn.hour)) }
        getTurnsFromDate(daySelected.value.toString())
        return events
    }
    init {
        daySelected.value = LocalDate(
            LocalDateTime.now().year,
            LocalDateTime.now().monthValue,
            LocalDateTime.now().dayOfMonth
        )
        setTurnSelected()
        getTurnsFromDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

    }

    fun saveTurn(save: Boolean) {
        Log.i("TAG", "saveTurn: " + turnSelected.value)
        viewModelScope.launch {
            turnSelected.value?.let {
                if (save) {
                    turnRepository.saveAll(it)    //.also { turnRepository.getAll() }
                } else {
                    turnRepository.update(it)
                }
            }
        }

    }

    private fun getTurnsFromDate(day: String) {
        viewModelScope.launch {
            turnsDay.value =
                turnRepository.getTurnsFromDate(day)
        }
    }

    fun setDaySelected(kalendarDay: KalendarDay) {
        Log.i("TAG", "setDaySelected: ${kalendarDay.localDate}")
        daySelected.value = kalendarDay.localDate
        turnSelected.value?.day = daySelected.value.toString()
        getTurnsFromDate(daySelected.value.toString())
    }

    fun setTurnSelected(turn: Turn) {
        Log.i("TAG", "setTurnSelected: $turn")
        turnSelected.value = turn
    }

    fun setTurnSelected() {
        turnSelected.value = Turn(
            id = UUID.randomUUID().toString(),
            day = daySelected.value.toString()
        )
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

}