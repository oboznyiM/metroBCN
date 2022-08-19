package com.example.metrobcn.views

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.metrobcn.R
import com.example.metrobcn.model.Line
import com.example.metrobcn.model.Station
import com.example.metrobcn.ui.theme.MetroBCNTheme
import com.example.metrobcn.ui.theme.fontSizeHeader
import com.example.metrobcn.ui.theme.fontSizeLarge
import com.example.metrobcn.ui.theme.fontSizeMedium
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase

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
                .height(50.dp)
                .background(color = Color.Blue)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = title,
                textAlign = TextAlign.Center,
                fontSize = fontSizeHeader,
                style = TextStyle(
                    color = Color.White
                )
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

class StationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.crashlytics.setUserId("user123456789")
        setContent {
            MetroBCNTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val x = null
                    val y = x!!

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DrawLine(Line(
                                id=123,
                                color="#228B22",
                                title="L3"
                            )
                            )
                            Text(
                                text = "Drassanes",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 30.dp, bottom = 20.dp, start = 25.dp),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = fontSizeHeader
                            )
                        }
                        Row {
                            Text(
                                modifier = Modifier.padding(start=20.dp),
                                text = "Next train in ",
                                fontSize = fontSizeLarge
                            )
                            Text(
                                text="5 minutes",
                                fontSize = fontSizeLarge,
                                style = TextStyle(
                                    color=Color.Red
                                )
                            )
                        }
                        Text(
                            text = "Transitions",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp, bottom = 20.dp, start = 25.dp),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = fontSizeHeader
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            val l3: Line = Line(123, "#228B22", "L3")
                            val l1: Line = Line(123, "#DC143C", "L1")

                            val l5: Line = Line(123, "#0000CD", "L5")
                            DrawLine(l1)
                            DrawLine(l3)
                            DrawLine(l5)
                            DrawLine(l1)
                            DrawLine(l3)
                            DrawLine(l5)
                            /*DrawLine("L1", Color.Red)
                            DrawLine("L2", Color.Blue)
                            DrawLine("L3", Color.Green)
                            DrawLine("L1", Color.Red)
                            DrawLine("L2", Color.Blue)
                            DrawLine("L3", Color.Green)
                            DrawLine("L1", Color.Red)
                            DrawLine("L2", Color.Blue)
                            DrawLine("L3", Color.Green)*/
                        }

                        Text(text = "What to see?",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp, bottom = 10.dp, start = 25.dp),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = fontSizeHeader
                        )

                        Sightseeing("Sagrada Familia")
                        Sightseeing("Sagrada Familia")
                        Sightseeing("Sagrada Familia")


                    }
                }
            }
        }
    }
}