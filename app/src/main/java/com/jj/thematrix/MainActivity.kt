package com.jj.thematrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jj.thematrix.core.presentation.design_system.components.GlowingText
import com.jj.thematrix.core.presentation.design_system.components.MatrixBG
import com.jj.thematrix.ui.theme.TheMatrixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheMatrixTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MatrixBG(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            GlowingText(
                                modifier = Modifier.align(Alignment.Center),
                                text = "The Matrix",
                                fontSize = 48.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
