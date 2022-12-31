package com.juliangg.nails.features.calendar

import android.util.Log
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent

data class CalendarDayEvent(
    val kalendarDay: KalendarDay,
    val kalendarEventList: List<KalendarEvent>
) {
    fun getCalendarDayEventList(): List<KalendarEvent> {
        val kalendarDayEvent: MutableList<KalendarEvent> = mutableListOf()
        kalendarDayEvent.clear()
        for (event: KalendarEvent in kalendarEventList) {
            if (event.date == kalendarDay.localDate) {
                kalendarDayEvent.add(event)
            }
        }
        return kalendarDayEvent;
    }
}
