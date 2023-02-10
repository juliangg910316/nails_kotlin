package com.juliangg.nails.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.juliangg.nails.database.turn.Turn
import com.juliangg.nails.utils.MaskTransformation

@Composable
fun EditTurnDialog(dialogState: MutableState<Boolean>, turn: Turn?) {

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
                        text = if (turn != null) "Editar turno" else "Nuevo turno",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                    MyBody()
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
fun MyBody() {
    var date by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var pay by remember {
        mutableStateOf("")
    }
    var payComplete by remember {
        mutableStateOf("")
    }


    Column(
        Modifier.padding(all = 8.dp)
    ) {
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text(text = "Hora") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Nombre") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                autoCorrect = true,
                capitalization = KeyboardCapitalization.Words
            )
        )
        OutlinedTextField(
            value = pay,
            onValueChange = { pay = it.filter { it.isDigit() } },
            label = { Text(text = "Pago adelantado") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = MaskTransformation()
        )
        OutlinedTextField(
            value = payComplete,
            onValueChange = { payComplete = it },
            label = { Text(text = "Pago total") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

