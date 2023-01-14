package com.juliangg.nails.features.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juliangg.nails.database.turn.TurnDao

/*
class CalendarViewModelFactory(private val turnDao: TurnDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalendarViewModel(turnDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
