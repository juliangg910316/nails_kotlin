package com.juliangg.nails.widgets

import com.juliangg.nails.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_baseline_home_24, "Inicio")
    object Calendar : NavigationItem("calendar", R.drawable.ic_baseline_calendar_month_24, "Turnos")
    object Store : NavigationItem("store", R.drawable.ic_baseline_store_24, "Salon")
    object Setting : NavigationItem("setting", R.drawable.ic_baseline_settings_24, "Config")
}
