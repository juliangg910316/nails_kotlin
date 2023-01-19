package com.juliangg.nails.features.calendar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import com.juliangg.nails.R
import com.juliangg.nails.ui.theme.*
import com.juliangg.nails.widgets.CompleteDialogContent

@Composable
fun CalendarScreen() {
    // Context to toast a message
    val ctx: Context = LocalContext.current

    val calendarViewModel = hiltViewModel<CalendarViewModel>()

    // Dialog state Manager
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    val calendarDayEventState by remember {
        calendarViewModel.calendarDayEventState
    }

    if (dialogState.value) {
        Dialog(
            onDismissRequest = { dialogState.value = false },
            content = {
                CompleteDialogContent("I am title", dialogState, "OK") {
                    Text(
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                        fontSize = 22.sp
                    )
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
        calendarViewModel.doSomethingMore()
    } else {
        Toast.makeText(ctx, "Dialog Closed", Toast.LENGTH_SHORT).show()
        calendarViewModel.doSomething()
    }

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
            kalendarEvents = calendarViewModel.kalendarEvent,
            onCurrentDayClick = { a, b ->
                calendarViewModel.addCalendarDayEvent(CalendarDayEvent(kalendarDay = a, kalendarEventList = b))
            },
        )
        MyTurnList(turnList = calendarDayEventState.getCalendarDayEventList(), dialogState = dialogState)
    }
}

@Composable
fun MyTurnList(modifier: Modifier = Modifier,turnList: List<KalendarEvent>, dialogState: MutableState<Boolean>) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        items(turnList) {
            data -> MySimpleListItem(event = data, dialogState = dialogState)
        }
    }
}

// The UI for each list item can be generated by a reusable composable
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MySimpleListItem(event: KalendarEvent, dialogState: MutableState<Boolean>) {
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
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_timer_24),
                    contentDescription = ""
                )
                Text(
                    text = event.eventDescription?:"",
                    style = MaterialTheme.typography.caption
                )
            }

           Column (
               modifier = Modifier
                   .padding(16.dp)
                   .align(Alignment.CenterVertically)
                   .weight(weight = 1f)
           ) {
               Text(text = event.eventName, style = MaterialTheme.typography.h6)
               Text(text = "55442211", style = MaterialTheme.typography.caption)
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