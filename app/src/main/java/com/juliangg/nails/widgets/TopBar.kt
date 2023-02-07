package com.juliangg.nails.widgets

import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.juliangg.nails.R
import com.juliangg.nails.features.calendar.CalendarScreen
import com.juliangg.nails.features.home.HomeScreen
import com.juliangg.nails.features.setting.SettingScreen
import com.juliangg.nails.features.store.StoreScreen
import com.juliangg.nails.ui.theme.Blue400
import com.juliangg.nails.ui.theme.Blue800

@Composable
fun TopBar(navController: NavHostController) {
    var title by remember {
        mutableStateOf("")
    }
    var hasActions by remember {
        mutableStateOf(false)
    }
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            title = "Home"
            hasActions = false
        }
        composable(NavigationItem.Calendar.route) {
            title = "Calendar"
            hasActions = true
        }
        composable(NavigationItem.Store.route) {
            title = "Store"
            hasActions = false
        }
        composable(NavigationItem.Setting.route) {
            title = "Setting"
            hasActions = false
        }
    }
    EditTurnDialog(dialogState = dialogState, turn = null)

    TopAppBar(
        title = { Text(text = title, fontSize = 18.sp)},
        actions= {
            if (hasActions) {
                IconButton(onClick = {
                    dialogState.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        },
        backgroundColor = Blue800,
        contentColor = Color.White
    )
}

/*
@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}*/
