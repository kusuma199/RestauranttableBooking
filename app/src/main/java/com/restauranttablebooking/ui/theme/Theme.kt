package com.restauranttablebooking.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColorScheme(
    primary = black,
    secondary = black,
    background = white
)

@Composable
fun RestaurantTableBookingAppTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )

}