package com.juliangg.nails.widgets

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.juliangg.nails.R
import com.juliangg.nails.database.turn.Turn
import com.juliangg.nails.features.calendar.CalendarViewModel
import com.juliangg.nails.ui.theme.NailsTheme
import com.juliangg.nails.ui.theme.PrimaryColor
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
                            Text(text = "Cancelar", fontSize = 20.sp, color = MaterialTheme.colors.primary)
                        }
                        TextButton(onClick = {
                            viewModel.saveTurn()
                            dialogState.value = false
                        }) {
                            Text(text = "Adicionar", fontSize = 20.sp, color = MaterialTheme.colors.primary)
                        }

                    }
                }
            }
        }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
        )
    }
}

@Composable
fun MyBody(viewModel: CalendarViewModel) {

    //val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    //val mMinute = mCalendar[Calendar.MINUTE]
    val time = viewModel.turnSelected.value?.hour?.split(" ")
    val hour = time?.get(0)?.split(":")

    // Value for storing time as a string
    var mTime: String by remember {
        mutableStateOf(viewModel.turnSelected.value?.hour ?: "")
    }
    var name by remember {
        mutableStateOf(viewModel.turnSelected.value?.nameClient ?: "")
    }
    var phone by remember {
        mutableStateOf(viewModel.turnSelected.value?.phoneClient ?: "")
    }
    var pay by remember {
        mutableStateOf(viewModel.turnSelected.value?.payPrevious ?: 0)
    }
    var payComplete by remember {
        mutableStateOf(viewModel.turnSelected.value?.payTotal ?: 0)
    }
    val checkedState = remember { mutableStateOf(false) }
    val fitHeight = 60.dp
    // Fetching local context
    val mContext = LocalContext.current

    // Creating a TimePicker dialod
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            val min = if (mMinute > 9) {
                "$mMinute"
            } else {
                "0$mMinute"
            }
            mTime = if (mHour > 12) {
                "${mHour - 12}:$min PM"
            } else if (mHour == 12){
                "$mHour:$min PM"
            } else {
                "$mHour:$min AM"
            }
            viewModel.setHour(mTime)
        }, Integer.valueOf(hour?.get(0) ?: "0"), Integer.valueOf(hour?.get(1) ?: "0"), false
    )

    Column(
        Modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), contentAlignment = Alignment.Center){
            Image(
                modifier = Modifier
                    .size(72.dp)
                    .clip(shape = CircleShape)
                    .border(1.5.dp, PrimaryColor, CircleShape),
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Your Image",
                contentScale = ContentScale.Crop
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                viewModel.setName(name)
            },
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
            onValueChange = {
                phone = it
                viewModel.setPhone(phone)
            },
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
        TextButton(
            onClick = { mTimePickerDialog.show() },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(top = 8.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(fitHeight)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Hora:",
                    color = Color.Gray,
                    modifier = Modifier.weight(2f, true),
                    style = MaterialTheme.typography.body1
                )
                Text(text = mTime, color = Color.Gray, style = MaterialTheme.typography.body1)
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.height(fitHeight)
        ) {
            Box(modifier = Modifier.weight(weight = 1f, fill = true)) {
                OutlinedTextField(
                    value = pay.toString(),
                    onValueChange = {
                        pay = Integer.parseInt(it.filter { it.isDigit() })
                        viewModel.setPayPrevious(pay)
                    },
                    label = { Text(text = "Pago adelantado") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(weight = 1f, fill = true)) {
                OutlinedTextField(
                    value = payComplete.toString(),
                    onValueChange = {
                        payComplete = Integer.parseInt(it.filter { it.isDigit() })
                        viewModel.setPayTotal(payComplete)
                    },
                    label = { Text(text = "Pago total") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                )
            }
        }
        Row(
            modifier = Modifier
                .height(fitHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "Falló")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
        }
    }
}