package com.jj.thematrix.core.presentation.design_system.components

import android.graphics.BlurMaskFilter
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jj.thematrix.ui.theme.TheMatrixTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun MatrixBG(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Box(modifier = modifier.fillMaxSize()) {
        MatrixRain()
        content()
    }

}

@Composable
fun MatrixRain(modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Calculate the number of columns based on the screen width
    val columns = with(density) { screenWidth.toPx().toInt() / 30 }
    val charSize = 30f

    val columnYPositions = remember {
        List(columns) { Random.nextInt(0, 1000).toFloat() }
    }.toMutableStateList()

    // Matrix characters: Latin, Japanese and etc
    val matrixChars = ('0'..'9') + ('A'..'Z') + ('ぁ'..'ん') + ('ア'..'ン')

    LaunchedEffect(Unit) {
        while (true) {
            delay(50L)
            columnYPositions.replaceAll {
                val next = it + Random.nextInt(15, 20)
                if (next > with(density) { screenHeight.toPx() }) 0f else next
            }
        }
    }

    Canvas(modifier = modifier
        .fillMaxSize()
        .background(Color.Black)) {
        val nativeCanvas = drawContext.canvas.nativeCanvas
        val paint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            textSize = charSize
            typeface = Typeface.MONOSPACE
        }

        for (i in columnYPositions.indices) {
            val x = i * charSize
            val yStart = columnYPositions[i]

            for (j in 0..20) {
                val y = yStart - j * charSize
                if (y < 0) continue

                val char = matrixChars.random()
                val alpha = (255 - j * 12).coerceIn(50, 255)

                // Glow effect (outer blur)
                paint.maskFilter = BlurMaskFilter(8f, BlurMaskFilter.Blur.NORMAL)
                paint.color = android.graphics.Color.argb(alpha, 0, 255, 70)
                nativeCanvas.drawText(char.toString(), x, y, paint)

                // Solid character (inner core)
                paint.maskFilter = null
                paint.color =
                    android.graphics.Color.argb(
                        (alpha * 1.2).toInt()
                            .coerceAtMost(255), 0, 255, 70
                    )
                nativeCanvas.drawText(char.toString(), x, y, paint)
            }
        }

    }
}

@Preview
@Composable
private fun MatrixBGPreview() {
    TheMatrixTheme {
        MatrixBG() {}
    }
}