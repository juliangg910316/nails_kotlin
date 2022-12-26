package com.juliangg.nails.widgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.juliangg.nails.R

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Calendar,
        NavigationItem.Store,
        NavigationItem.Setting
    )
    BottomNavigation(backgroundColor = colorResource(id = R.color.primary), contentColor = Color.White) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = false, 
                onClick = { /*TODO*/ },
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.title)},
                label = { Text(text = item.title)},
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
            ) 
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}