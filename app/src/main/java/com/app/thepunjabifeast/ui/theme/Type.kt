package com.app.thepunjabifeast.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.thepunjabifeast.R
import androidx.compose.ui.text.font.Font

val AlmendraDisplay = FontFamily(
    Font(R.font.almendra_display)
)

val Arizonia = FontFamily(
    Font(R.font.arizonia)
)

val Cabin = FontFamily(
    Font(R.font.cabin_bold),
    Font(R.font.cabin_regular),
    Font(R.font.cabin_medium)
)

val Diplomata = FontFamily(
    Font(R.font.diplomata)
)

val JimNightshade = FontFamily(
    Font(R.font.jim_nightshade)
)

val MySoul = FontFamily(
    Font(R.font.my_soul)
)

val Neonderthaw = FontFamily(
    Font(R.font.neonderthaw)
)

val Satisfy = FontFamily(
    Font(R.font.satisfy)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AlmendraDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = Arizonia,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = Diplomata,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = JimNightshade,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = MySoul,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Satisfy,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

)