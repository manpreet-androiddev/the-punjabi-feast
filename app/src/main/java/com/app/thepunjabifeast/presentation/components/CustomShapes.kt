package com.app.thepunjabifeast.presentation.components

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush

val gradientColors: Brush
    get() = Brush.verticalGradient(
        listOf(
            Color(0xFFFFE1C1),   // Light peach (top)
            Color(0xFFFFB377),  // Mid peach
            Color(0xFFFFB377),
            //   Color(0xFFFF9950)   // Warm orange (bottom)
        )
    )

val FlippedWaveFooter = GenericShape { size, _ ->
    val width = size.width
    val height = size.height

    // green curve
    moveTo(0f, height)
    lineTo(0f, height * 0.45f)

    cubicTo(
        width * 0.25f, height * 0.60f,
        width * 0.75f, height * 0.30f,
        width, height * 0.45f
    )

    lineTo(width, height)
    close()
}

val CenterCurveRectangleShape = GenericShape { size, _ ->
    val width = size.width
    val height = size.height
    val radius = height * 0.2f

    moveTo(0f, height - radius)
    quadraticTo(0f, height, radius, height)
    quadraticTo(width / 2, height * 0.9f, width - radius, height)
    quadraticTo(width, height, width, height - radius)
    lineTo(width, radius)
    quadraticTo(width, 0f, width - radius, 0f)
    quadraticTo(width / 2, height * 0.1f, radius, 0f)
    quadraticTo(0f, 0f, 0f, radius)
    close()

}

val BottomOutCurveShape = GenericShape { size, _ ->
    val width = size.width // Canvas width
    val height = size.height // Canvas height

    moveTo(0f, 0f) // Top-left corner
    lineTo(width, 0f) // Top-right corner
    lineTo(width, height - 20f) // Right side slightly above bottom-right corner
    quadraticTo(
        width / 2, height + 60f, // Control point for the curve
        0f, height - 20f // End at the bottom-left corner
    )
    close() // Complete the path
}


