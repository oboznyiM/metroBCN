package com.example.metrobcn.views

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.metrobcn.LineViewModel
import com.example.metrobcn.model.Line
import com.example.metrobcn.model.LineToPass
import com.example.metrobcn.model.StationResponse
import com.example.metrobcn.ui.theme.MetroBCNTheme
import com.example.metrobcn.ui.theme.fontSizeHeader
import kotlinx.serialization.json.Json

@Composable
fun StationInLine(title: String, line: LineToPass) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {

        Text (
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .wrapContentHeight(),
            overflow = TextOverflow.Ellipsis,

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
                    color = Color(android.graphics.Color.parseColor(line.color)),
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

@Composable
fun TestList(stations: List<StationResponse>) {
    Text(text = stations.toString())
}

class LineActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val line: LineToPass = getIntent().getExtras()?.getParcelable("LINE") ?: LineToPass(123, "#000000", "lol")
        val viewModel = LineViewModel()

        super.onCreate(savedInstanceState)
        Log.d("LINE", line.toString())
        setContent {
            MetroBCNTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    val stations = viewModel.stations.observeAsState()
                    Log.d("APIQUERY", line.toString())
                    viewModel.getStationsList(line.id.toString())
                    Column (
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text (
                                text = "Line "+ line.title,
                                modifier = Modifier.fillMaxWidth().padding(top=30.dp, bottom=20.dp, start=25.dp),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = fontSizeHeader
                            )
                            /*DrawLine(
                                title = "L1",
                                color = Color.Red
                            )*/
                        }
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Log.d("LINEA", stations.value.toString())

                            if (stations.value!!.size > 0) {

                                stations.value!![0].features.forEach { station ->
                                    Log.d("LINEA", station.properties.toString())
                                    StationInLine(
                                        title = station.properties.NOM_PARADA,
                                        line = line
                                    )
                                }
                            }
                            /*StationInLine(title = "Station 1", line)
                            StationInLine(title = "Station 2", line)
                            StationInLine(title = "Station 3", line)
                            StationInLine(title = "Station 4", line)
                            StationInLine(title = "Station 5", line)
                            StationInLine(title = "Station 6", line)
                            StationInLine(title = "Station 7", line)
                            StationInLine(title = "Station 8", line)
                            StationInLine(title = "Station 9", line)
                            StationInLine(title = "Station 10", line)
                            StationInLine(title = "Station 11", line)
                            StationInLine(title = "Station 12", line)
                            StationInLine(title = "Station 13", line)
                            StationInLine(title = "Station 14", line)
                            StationInLine(title = "Station 15", line)*/
                        }
                    }
                }
            }
        }
    }
}