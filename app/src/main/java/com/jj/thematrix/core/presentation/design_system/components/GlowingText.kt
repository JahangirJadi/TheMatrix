package com.jj.thematrix.core.presentation.design_system.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.jj.thematrix.ui.theme.TheMatrixTheme


@Composable
fun GlowingText(
    modifier: Modifier = Modifier, text: String,
    fontSize: TextUnit = 48.sp,
    blueRadius: Float = 30f
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0E1501),
        style = TextStyle(
            shadow = Shadow(
                color = Color.Green,
                offset = Offset(0f, 0f),
                blurRadius = blueRadius
            )
        )
    )
}

@Preview
@Composable
private fun TheMatrixGlowingTextPreview() {
    TheMatrixTheme {
        GlowingText(text = "The Matrix")
    }

}