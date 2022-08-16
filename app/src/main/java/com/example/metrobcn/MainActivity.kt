package com.example.metrobcn

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metrobcn.ui.theme.MetroBCNTheme

@Composable
fun Line(title: String, color: Color) {
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(context, LineActivity::class.java))
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)

    ) {
    Box (
        modifier = Modifier.padding(15.dp),
        contentAlignment = Alignment.Center
    ){

        Canvas(modifier = Modifier.size(50.dp), onDraw = {
            drawCircle(color = color)
        })
        Text(
            text = title,
            style = TextStyle(
                color = Color.White
            )
        )}
    }
}

@Composable
fun Station(title: String, color: Color) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 30.dp, start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = color)
        
    ) {
        Button(
            onClick = {
                context.startActivity(Intent(context, StationActivity::class.java))
            },
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {}
        Text(text = title)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MetroBCNTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(text = "All Lines")
                        Row (
                            modifier = Modifier.fillMaxWidth()
                                ) {
                            Line("L1", Color.Red)
                            Line("L2", Color.Blue)
                            Line("L3", Color.Green)
                        }
                        Text (
                            text = "Home"
                        )
                        Station (
                            "Drassanes",
                            Color.Green
                        )
                        Text(text = "Favourite Stations")
                        Station (
                            "Barceloneta",
                            Color.Yellow
                        )
                        Station(
                            "Sants Estacio",
                            Color.Blue
                        )
                        Station(
                            "Espanya",
                            Color.Red
                        )
                    }
                }
            }
        }
    }
}
