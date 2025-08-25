package com.app.thepunjabifeast.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/*
bottomStart = 25.dp,
topStart = 50.dp,
topEnd = 0.dp,
bottomEnd = 15.dp*/

/*
topStart = 15.dp,
bottomStart = 50.dp,
topEnd = 50.dp,
bottomEnd = 15.dp*/

/*
topStart = 0.dp,
bottomStart = 50.dp,
topEnd = 0.dp,
bottomEnd = 50.dp*/

/*bottomStart = 16.dp, topEnd = 16.dp*/

/*
val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp),
    large = RoundedCornerShape(50.dp)
)*/

val Shapes = Shapes(

    extraLarge = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp),
    medium = RoundedCornerShape(8.dp),

    extraSmall = CutCornerShape(topEnd = 5.dp, bottomStart = 5.dp),
    small = CutCornerShape(topEnd = 8.dp, bottomStart = 8.dp),
    large = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
)