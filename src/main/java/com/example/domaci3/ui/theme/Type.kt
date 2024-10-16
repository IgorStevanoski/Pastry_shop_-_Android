package com.example.domaci3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.domaci3.R

val pacificoFF = FontFamily(
    Font(R.font.pacifico, FontWeight.Normal)
)

val lightblue = Color(0xFFA2D2FF)
val darkblue = Color(0xFF4C5760)
val lightestbrown = Color(0xFFD9D9D9)
val lightbrown = Color(0xFFD7CEB2)
val brown = Color(0xFFA59E8C)
val darkbrown = Color(0xFF66635B)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = pacificoFF,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = pacificoFF,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 48.sp,
        letterSpacing = 0.5.sp,
        color = darkbrown,
        textAlign = TextAlign.Center
    ),
    bodySmall = TextStyle(
        fontFamily = pacificoFF,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = darkbrown,
        textAlign = TextAlign.Center
    ),
    headlineLarge = TextStyle(
        fontFamily = pacificoFF,
        fontWeight = FontWeight.Normal,
        fontSize = 64.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = pacificoFF,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = darkblue
    ),
    labelSmall = TextStyle(
        fontFamily = pacificoFF,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = lightblue,
    )


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
