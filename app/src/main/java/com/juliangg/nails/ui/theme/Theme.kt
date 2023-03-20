package com.juliangg.nails.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Blue800,
    primaryVariant = Blue900,
    onPrimary = white,
    secondary = teal200,
    secondaryVariant = teal200,
    onSecondary = black,
    surface = black,
    onSurface = white,
    background = black,
    onBackground = white,
)

private val LightColorPalette = lightColors(
    primary = Blue800,
    primaryVariant = Blue900,
    onPrimary = white,
    secondary = teal200,
    secondaryVariant = teal700,
    onSecondary = black,
    surface = white,
    onSurface = black,
    background = white,
    onBackground = black,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun NailsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}