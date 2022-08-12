package com.example.metrobcn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.metrobcn.ui.theme.MetroBCNTheme

@Composable
fun Sightseeing(title: String) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 30.dp, end = 30.dp, top = 30.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Blue)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Text(text = title)
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

class StationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MetroBCNTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Line(title = "L3", color = Color.Green)
                            Text(
                                text = "Drassanes"
                            )
                        }
                        Text(text = "Next train in 5 minutes")
                        Text(text = "Transitions")
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            Line("L1", Color.Red)
                            Line("L2", Color.Blue)
                            Line("L3", Color.Green)
                            Line("L1", Color.Red)
                            Line("L2", Color.Blue)
                            Line("L3", Color.Green)
                            Line("L1", Color.Red)
                            Line("L2", Color.Blue)
                            Line("L3", Color.Green)
                        }

                        Text(text = "What to see?")

                        Sightseeing("Sagrada Familia")
                        Sightseeing("Sagrada Familia")
                        Sightseeing("Sagrada Familia")


                    }
                }
            }
        }
    }
}