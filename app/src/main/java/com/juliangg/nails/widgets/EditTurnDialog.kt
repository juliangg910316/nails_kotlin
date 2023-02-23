package com.juliangg.nails.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.juliangg.nails.database.turn.Turn
import com.juliangg.nails.features.calendar.CalendarViewModel
import com.juliangg.nails.utils.DateMaskTransformation
import com.juliangg.nails.utils.MaskTransformation


@Composable
fun EditTurnDialog(dialogState: MutableState<Boolean>, viewModel: CalendarViewModel) {
    Log.i("TAG", "EditTurnDialog: turn = ${viewModel.turnSelected.value}")

    if (dialogState.value) {
        Dialog(onDismissRequest = { dialogState.value = false }, content = {
            Card(
                modifier = Modifier.fillMaxWidth(1f), shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (viewModel.turnSelected.value?.nameClient?.isBlank() == true) "Nuevo turno" else "Editar turno",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                    MyBody(viewModel)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { dialogState.value = false }) {
                            Text(text = "Cancelar", fontSize = 20.sp)
                        }
                        TextButton(onClick = {
                            viewModel.saveTurn()
                            dialogState.value = false
                        }) {
                            Text(text = "Adicionar", fontSize = 20.sp)
                        }

                    }
                }
            }
        }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
        )
        //calendarViewModel.doSomethingMore()
    } else {
        //Toast.makeText(ctx, "Dialog Closed", Toast.LENGTH_SHORT).show()
        //calendarViewModel.doSomething()
    }
}

@Composable
fun MyBody(viewModel: CalendarViewModel) {
    var hour: String by remember {
        mutableStateOf(viewModel.turnSelected.value?.hour ?: "")
    }
    var name by remember {
        mutableStateOf(viewModel.turnSelected.value?.nameClient ?: "")
    }
    var phone by remember {
        mutableStateOf(viewModel.turnSelected.value?.phoneClient ?: "")
    }
    var pay by remember {
        mutableStateOf(viewModel.turnSelected.value?.payPrevious ?: "")
    }
    var payComplete by remember {
        mutableStateOf(viewModel.turnSelected.value?.payTotal ?: "")
    }
    val checkedState = remember { mutableStateOf(false) }
    val fitHeight = 60.dp


    Column(
        Modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.height(fitHeight)
        ) {
            Box(
                modifier = Modifier
                    .weight(weight = 3f, fill = true)
                    .fillMaxHeight()
            ) {
                OutlinedTextField(
                    value = hour,
                    onValueChange = { hour = it.filter { it.isDigit() } },
                    label = { Text(text = "Hora") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = DateMaskTransformation(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            DropdownTime(
                Modifier
                    .weight(weight = 1f)
                    .height(52.dp)
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)),
                viewModel = viewModel
            )

        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Nombre") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                autoCorrect = true,
                capitalization = KeyboardCapitalization.Words
            ),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            modifier = Modifier
                .height(fitHeight)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text(text = "Número Móvil") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                autoCorrect = true
            ),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            modifier = Modifier
                .height(fitHeight)
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.height(fitHeight)
        ) {
            Box(modifier = Modifier.weight(weight = 1f, fill = true)) {
                OutlinedTextField(
                    value = pay,
                    onValueChange = { pay = it.filter { it.isDigit() } },
                    label = { Text(text = "Pago adelantado") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = MaskTransformation(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(weight = 1f, fill = true)) {
                OutlinedTextField(
                    value = payComplete,
                    onValueChange = { payComplete = it.filter { it.isDigit() } },
                    label = { Text(text = "Pago total") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = MaskTransformation(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                )
            }
        }
        Row (
            modifier = Modifier
                .height(fitHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
                ){
            Text(text = "Falló")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
        }
    }
}

@Composable
fun DropdownTime(modifier: Modifier = Modifier, viewModel: CalendarViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("AM", "PM")
    var selectedIndex by remember { mutableStateOf(if (viewModel.turnSelected.value?.am == true) 0 else 1) }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .clickable(onClick = { expanded = true }),
            textAlign = TextAlign.Center

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(
                    Color.Gray
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = s, style = TextStyle(color = Color.White))
                }
            }
        }
    }
}

