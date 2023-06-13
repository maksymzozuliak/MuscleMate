package com.zozuliak.musclemate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zozuliak.musclemate.R


val geologicaFontFamily = FontFamily(
    Font(R.font.geologica_black),
    Font(R.font.geologica_bold, weight = FontWeight.Bold),
    Font(R.font.geologica_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.geologica_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.geologica_light, weight = FontWeight.Light),
    Font(R.font.geologica_medium, weight = FontWeight.Medium),
    Font(R.font.geologica_regular, weight = FontWeight.Normal),
    Font(R.font.geologica_semibold, weight = FontWeight.SemiBold),
    Font(R.font.geologica_thin, weight = FontWeight.Thin)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = geologicaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = geologicaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
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