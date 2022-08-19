package com.example.metrobcn.views

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metrobcn.AppApplication
import com.example.metrobcn.MainViewModel
import com.example.metrobcn.model.Line
import com.example.metrobcn.model.LineToPass
import com.example.metrobcn.model.TimelineResponse
import com.example.metrobcn.retrofit.TransportApiClient
import com.example.metrobcn.retrofit.TransportApiProvider
import com.example.metrobcn.ui.theme.MetroBCNTheme
import com.example.metrobcn.ui.theme.fontSizeHeader
import com.example.metrobcn.ui.theme.fontSizeLarge
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.tmb.cat/v1/"

@Composable
fun DrawLine(line: Line) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, LineActivity::class.java)
            intent.putExtra("LINE", LineToPass(line.id, line.color, line.title))
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)

    ) {
    Box (
        modifier = Modifier.padding(0.dp),
        contentAlignment = Alignment.Center
    ){

        Canvas(modifier = Modifier.size(70.dp), onDraw = {
            drawCircle(color =  Color(android.graphics.Color.parseColor(line.color)))
        })
        Text(
            text = line.title,
            style = TextStyle(
                color = Color.White,
            ),
            fontWeight = FontWeight.Bold,
            fontSize = fontSizeLarge
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
        Column (
            modifier = Modifier.fillMaxSize()
                ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = title,
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    color = Color.White
                )
            )
            Row (
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.padding(start=50.dp),
                    text = "L3",
                    fontSize = 80.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.White
                    )
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, end = 15.dp),
                        textAlign = TextAlign.End,
                        text = "StartStation",
                        fontSize = 25.sp,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, end = 15.dp),
                        textAlign = TextAlign.End,
                        text = "EndStation",
                        fontSize = 25.sp,
                    )
                }
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    @Composable
    fun ScreenSetup(viewModel: MainViewModel) {

        val allLines by viewModel.lines.observeAsState(listOf())

        MainScreen(
            allLines = allLines,
            viewModel = viewModel
        )
    }

    @Composable
    fun MainScreen(
        allLines: List<Line>,
        viewModel: MainViewModel
    ) {
        Log.d("APIQUER", allLines.toString())

        Log.d("APIQUER", viewModel.getLineById(5).value.toString())
        viewModel.updateLines()
        Column(

            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                modifier = Modifier
                    .padding(top = 30.dp, start = 30.dp, bottom = 20.dp),
                fontSize = fontSizeHeader,
                text = "All Lines"
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(start = 30.dp)
            ) {
                allLines.forEach {line -> DrawLine(line)}
                /*DrawLine("L1", Color.Red)
                DrawLine("L2", Color.Blue)
                DrawLine("L3", Color.Green)

                DrawLine("L1", Color.Red)
                DrawLine("L2", Color.Blue)
                DrawLine("L3", Color.Green)*/
            }
            Text (

                modifier = Modifier
                    .padding(top = 30.dp, start = 30.dp),
                fontSize = fontSizeHeader,
                text = "Home"
            )
            Station (
                "Drassanes",
                Color(0xFF228B22)
            )
            Text(
                modifier = Modifier
                    .padding(top = 40.dp, start = 30.dp, bottom = 0.dp),
                fontSize = fontSizeHeader,
                text = "Favourite Stations"
            )
            Station (
                "Barceloneta",
                Color(0xFFFFCB40)
            )
            Station(
                "Sants Estacio",
                Color(0xFFDC143C)
            )
            Station(
                "Espanya",
                Color(0xFF0000CD)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //addLines()
        //showLines()
        setContent {
            MetroBCNTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: MainViewModel = viewModel(
                            it,
                            "MainViewModel",
                            MainViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application
                            )
                        )

                        ScreenSetup(viewModel)
                    }
                }
            }
        }
    }
    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TransportApiClient::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<TimelineResponse?> {
            override fun onResponse(
                call: Call<TimelineResponse?>,
                response: Response<TimelineResponse?>
            ) {
                val responseBody = response.body()
                Log.d("APIQUERY", responseBody.toString())
            }

            override fun onFailure(call: Call<TimelineResponse?>, t: Throwable) {
                Log.d("APIQUERY", "error")
            }
        })
    }

    fun showLines() {
        /*TransportApiProvider.fetchLines(viewModel)
        viewModel.getLinesFromDatabase().observe(this) {
            Log.d("DBASE", " Found on db : $it")
        }*/
    }

    fun addLines() {
        /*viewModel.addLine(Line(
            124,
            "#FFFFFF",
            "L5"
        ))*/
    }

}

class MainViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}
