package com.juliangg.nails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.juliangg.nails.ui.theme.NailsTheme
import com.juliangg.nails.widgets.BottomNavigationBar
import com.juliangg.nails.widgets.TopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar() },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                /* Add code later */
            }
        },
        backgroundColor = colorResource(id = R.color.white)
        )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NailsTheme {
        MainScreen()
    }
}