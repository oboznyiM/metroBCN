package com.example.metrobcn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.metrobcn.ui.theme.MetroBCNTheme

@Composable
fun StationInLine(title: String) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {

        Text (
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(),
            text = title,
        )
        Row (
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Canvas(
                modifier = Modifier
                    .width(50.dp)
                    .fillMaxHeight()

            ) {
                val canvasQuadrantSize = size
                drawRect(
                    color = Color.Red,
                    size = canvasQuadrantSize
                )
                drawCircle(
                    radius = 20f,
                    color = Color.Black
                )
            }
        }
    }
}


class LineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MetroBCNTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column (
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text (
                               text = "Line L1"
                            )
                            Line(
                                title = "L1",
                                color = Color.Red
                            )
                        }
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            StationInLine(title = "Station 1")
                            StationInLine(title = "Station 2")
                            StationInLine(title = "Station 3")
                            StationInLine(title = "Station 4")
                            StationInLine(title = "Station 5")
                            StationInLine(title = "Station 6")
                            StationInLine(title = "Station 7")
                            StationInLine(title = "Station 8")
                            StationInLine(title = "Station 9")
                            StationInLine(title = "Station 10")
                            StationInLine(title = "Station 11")
                            StationInLine(title = "Station 12")
                            StationInLine(title = "Station 13")
                            StationInLine(title = "Station 14")
                            StationInLine(title = "Station 15")
                        }
                    }
                }
            }
        }
    }
}