package com.juliangg.nails.features.calendar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.model.KalendarType
import com.juliangg.nails.R
import com.juliangg.nails.database.turn.Turn
import com.juliangg.nails.ui.theme.*
import com.juliangg.nails.widgets.EditTurnDialog

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalendarScreen() {

    val calendarViewModel = hiltViewModel<CalendarViewModel>()

    // Dialog state Manager
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    val turnsDay: List<Turn> by calendarViewModel.turnsDay.collectAsState(
        initial = emptyList()
    )

    val allTurns: List<Turn> by calendarViewModel.allTurns.collectAsState(initial = emptyList())

    EditTurnDialog(dialogState = dialogState, viewModel = calendarViewModel)

    Scaffold (
        topBar = {TopAppBar(
            title = { Text(text = "Calendario", fontSize = 18.sp)},
            actions= {
                IconButton(onClick = {
                    dialogState.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            },
            backgroundColor = Blue800,
            contentColor = Color.White
        )   },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.white))
                        .wrapContentSize(Alignment.TopCenter)
                ) {
                    Kalendar(
                        kalendarType = KalendarType.Firey,
                        kalendarThemeColor = KalendarThemeColor(Color.White, Blue800, Blue800),
                        kalendarDayColors = KalendarDayColors(Color.Black, Color.White),
                        kalendarEvents = calendarViewModel.getKalendarEvent(allTurns),
                        onCurrentDayClick = { a, _ ->
                            calendarViewModel.setDaySelected(kalendarDay = a)
                        },
                    )
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Log.i("TAG", "CalendarScreen: $turnsDay")
                        items(turnsDay) { data ->
                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 12.dp)
                                    .fillMaxWidth(),
                                elevation = 10.dp,
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                                onClick = {
                                    calendarViewModel.setTurnSelected(data)
                                    dialogState.value = true
                                }
                            ) {
                                Row {
                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .align(Alignment.CenterVertically)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_timer_24),
                                            contentDescription = ""
                                        )
                                        Text(
                                            text = data.payPrevious.toString(),
                                            style = MaterialTheme.typography.caption
                                        )
                                    }

                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .align(Alignment.CenterVertically)
                                            .weight(weight = 1f)
                                    ) {
                                        Text(text = data.nameClient, style = MaterialTheme.typography.h6)
                                        Text(text = data.phoneClient, style = MaterialTheme.typography.caption)
                                    }
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_navigate_next_24),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
            )

}

/*@Composable
fun MyTurnList(
    modifier: Modifier = Modifier,
    turnList: List<Turn>,
    dialogState: MutableState<Boolean>
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        items(turnList) { data ->
            MySimpleListItem(event = data, dialogState = dialogState)
        }
    }
}*/

// The UI for each list item can be generated by a reusable composable
/*
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MySimpleListItem(event: Turn, dialogState: MutableState<Boolean>) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            dialogState.value = true
        }
    ) {
        Row(

        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_timer_24),
                    contentDescription = ""
                )
                Text(
                    text = event.payPrevious,
                    style = MaterialTheme.typography.caption
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .weight(weight = 1f)
            ) {
                Text(text = event.nameClient, style = MaterialTheme.typography.h6)
                Text(text = event.phoneClient, style = MaterialTheme.typography.caption)
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_navigate_next_24),
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}*/
