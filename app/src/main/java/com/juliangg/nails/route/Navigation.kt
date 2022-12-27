package com.juliangg.nails.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.juliangg.nails.features.calendar.CalendarScreen
import com.juliangg.nails.features.home.HomeScreen
import com.juliangg.nails.features.setting.SettingScreen
import com.juliangg.nails.features.store.StoreScreen
import com.juliangg.nails.widgets.NavigationItem

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Calendar.route) {
            CalendarScreen()
        }
        composable(NavigationItem.Store.route) {
            StoreScreen()
        }
        composable(NavigationItem.Setting.route) {
            SettingScreen()
        }
    }
}