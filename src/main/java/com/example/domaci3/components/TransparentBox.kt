package com.example.domaci3.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


// Zameniti sa marginama
@Composable
fun TransparentBox (
    height: Dp = 150.dp
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color.Transparent)
    )
}