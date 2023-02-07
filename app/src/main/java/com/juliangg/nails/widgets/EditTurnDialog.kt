package com.juliangg.nails.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.juliangg.nails.database.turn.Turn

@Composable
fun EditTurnDialog(dialogState: MutableState<Boolean>, turn: Turn?){

    if (dialogState.value) {
        Dialog(
            onDismissRequest = { dialogState.value = false },
            content = {
                Card(
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .fillMaxWidth(1f), shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(1f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if(turn != null) "Edit turn" else "New turn",
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
                            TextButton(
                                onClick = { dialogState.value = false }
                            ) {
                                Text(text = "Cancelar", fontSize = 20.sp)
                            }
                            TextButton(
                                onClick = {
                                    dialogState.value = false
                                }
                            ) {
                                Text(text = "Adicionar", fontSize = 20.sp)
                            }

                        }
                    }
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
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

}