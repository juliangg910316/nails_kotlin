package com.juliangg.nails.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.juliangg.nails.R
import com.juliangg.nails.ui.theme.Blue400
import com.juliangg.nails.ui.theme.Blue800

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home View",
            fontWeight = FontWeight.Bold,
            color = Blue800,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        OutlinedTextField(value = "", onValueChange = {}, label = { Text(text = "First name") },)
    }
}