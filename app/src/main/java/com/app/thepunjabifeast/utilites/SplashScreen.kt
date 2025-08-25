package com.app.thepunjabifeast.utilites

import android.graphics.Typeface
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import kotlin.math.cos
import kotlin.math.sin

/* Card(
            shape = CurvedShape(cornerFraction = 0.2f),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE87803) // your custom background color
            ),
        ) */


/*Brand image: 160×160 dp
App icon with an icon background: 240×240 dp (fits within a circle 160 dp in diameter)
App icon without an icon background: 288×288 dp (fits within a circle 192 dp in diameter)*/
@Preview(showBackground = true)
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {

    Column( modifier = Modifier
        .padding(16.dp)) {

        BottomOuterCurveShapeWrapper(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CurvedText(
                    text = "It's time to enjoy",
                    radius = 160f,
                    center = Offset(400f, 300f), // adjust center as needed
                    startAngle = -60f,
                    sweepAngle = 240f
                )
                Text(
                    text = "hello World",
                    color = Color.Red,
                    fontSize = 36.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun Shape() {
    Box(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        val list =  listOf(Color(0xFF5936B4), Color(0xFF362A84))

        Canvas(
            modifier = Modifier
                .width(250.dp)
                .height(150.dp)
        ) {
            val rect = Rect(Offset.Zero, size)

              val path = Path().apply {
                  lineTo(0f, size.height)
                  lineTo(size.width, size.height)
                  lineTo(size.width, (size.height / 2.5).toFloat())
                  close()
              }

            val paint =  Paint().apply {
                shader = LinearGradientShader(
                    rect.topLeft,
                    rect.bottomRight,
                    list
                )
                pathEffect = PathEffect.cornerPathEffect(60f)
            }
            drawIntoCanvas { canvas ->
                canvas.drawPath(
                    path = path,
                    paint = paint
                )
            }
        }


    }
}

@Preview
@Composable
fun AnimatedWavyHeader() {
    val backgroundColor = Color(0xFF4CAF50)
    val infiniteTransition = rememberInfiniteTransition()
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = -30f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, height * 0.6f)

            cubicTo(
                width * 0.25f, height * 0.4f + waveOffset,
                width * 0.75f, height * 0.8f - waveOffset,
                width, height * 0.6f
            )

            lineTo(width, 0f)
            close()
        }

        drawPath(
            path = path,
            color = backgroundColor
        )
    }
}

@Preview
@Composable
fun DualWaveHeader(
    modifier: Modifier = Modifier,
    colorPrimary: Color = Color(0xFF4CAF50),
    colorSecondary: Color = Color(0xFFB2DFDB)
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        val width = size.width
        val height = size.height

        // Primary (green) wave
        val wave1 = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, height * 0.55f)

            cubicTo(
                width * 0.25f, height * 0.40f,
                width * 0.75f, height * 0.70f,
                width, height * 0.55f
            )

            lineTo(width, 0f)
            close()
        }


        // Secondary (lighter) wave
        val wave2 = Path().apply {
            moveTo(0f, height * 0.60f)

            cubicTo(
                width * 0.25f, height * 0.45f,
                width * 0.75f, height * 0.75f,
                width, height * 0.60f
            )

            lineTo(width, height)
            lineTo(0f, height)
            close()
        }

        drawPath(path = wave2, color = colorSecondary)
        drawPath(path = wave1, color = colorPrimary)
    }
}

@Preview
@Composable
fun FlippedDualWaveFooter(
    modifier: Modifier = Modifier,

) {
    val colorPrimary: Color = Color(0xFF4CAF50)
    val colorSecondary: Color = Color(0xFFB2DFDB)
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        val width = size.width
        val height = size.height

        // Primary (green) wave on top
        val wave1 = Path().apply {
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

        // Secondary (lighter) wave beneath it
        val wave2 = Path().apply {
            moveTo(0f, height * 0.40f)

            cubicTo(
                width * 0.25f, height * 0.55f,
                width * 0.75f, height * 0.25f,
                width, height * 0.40f
            )

            lineTo(width, 0f)
            lineTo(0f, 0f)
            close()
        }

        drawPath(path = wave2, color = colorSecondary)
        drawPath(path = wave1, color = colorPrimary)
    }
}

/*class CenterCurveRectangleShape1(private val cornerFraction: Float = 0.2f) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width = size.width
        val height = size.height
        val radius = height * cornerFraction

        val path = Path().apply {
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

        return Outline.Generic(path)
    }
}*/

@Composable
fun BottomOuterCurveShapeWrapper(
    modifier: Modifier = Modifier, // Modifier to apply from parent
    backgroundColor: Color = Color(0xFF1C1F2A), // Background color of the shape
    cornerRadiusFraction: Float = 0.2f, // Corner radius as a fraction of height
    content: @Composable BoxScope.() -> Unit // Content to place inside the shape
) {
    Box(
        modifier = modifier.drawBehind { // Draw the custom background shape behind the Box content
            val width = size.width // Canvas width
            val height = size.height // Canvas height
            val radius = height * cornerRadiusFraction // Compute corner radius

            // Define the path for the custom shape
            val path = Path().apply {
                moveTo(0f, 0f) // Top-left corner
                lineTo(width, 0f) // Top-right corner
                lineTo(width, height - 20f) // Right side slightly above bottom-right corner
                quadraticTo(
                    width / 2, height + 60f, // Control point for the curve
                    0f, height - 20f // End at the bottom-left corner
                )
                close() // Complete the path
            }

            // Draw the path with the specified color and fill style
            drawPath(
                path = path,
                color = backgroundColor,
                style = Fill
            )
        }
    ) {
        content() // Place user content inside the Box
    }
}

@Composable
fun CurvedText(
    text: String,
    radius: Float = 150f,
    center: Offset = Offset(200f, 200f),
    startAngle: Float = -100f, // Adjust to control where arc starts
    sweepAngle: Float = 200f, // Total angle covered by text
    style: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFE87803)
    )
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val charAngles = sweepAngle / text.length
        val paint = android.graphics.Paint().apply {
            color = style.color.toArgb()
            textSize = style.fontSize.toPx()
            isAntiAlias = true
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        }

        text.forEachIndexed { index, c ->
            val angle = startAngle + index * charAngles
            val theta = Math.toRadians(angle.toDouble())
            val x = center.x + radius * cos(theta).toFloat()
            val y = center.y + radius * sin(theta).toFloat()

            // Rotate canvas to place the character upright
            withTransform({
                rotate(angle + 90, Offset(x, y)) // +90 to make text upright
            }) {
                drawContext.canvas.nativeCanvas.drawText(c.toString(), x, y, paint)
            }
        }
    }
}