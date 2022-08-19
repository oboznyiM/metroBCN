package com.example.metrobcn.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.metrobcn.LineViewModel
import com.example.metrobcn.MainViewModel
import com.example.metrobcn.model.Line
import com.example.metrobcn.model.LineResponse
import com.example.metrobcn.model.StationResponse
import com.example.metrobcn.model.TimelineResponse
import com.example.metrobcn.views.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.tmb.cat/v1/"

class TransportApiProvider {
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(com.example.metrobcn.views.BASE_URL)
                .build()
                .create(TransportApiClient::class.java)
        }


        fun fetchLines(viewModel: MainViewModel) {
            val retrofitData = retrofit.getLines()

            retrofitData.enqueue(object : Callback<LineResponse?> {
                override fun onResponse(
                    call: Call<LineResponse?>,
                    response: Response<LineResponse?>
                ) {
                    val responseBody = response.body()
                    Log.d("APIQUERY", responseBody.toString())
                    responseBody!!.features.forEach { line -> viewModel.addLine(Line(
                        id=line.properties.CODI_LINIA,
                        color='#'+line.properties.COLOR_LINIA,
                        title=line.properties.NOM_LINIA
                    ))}
                }

                override fun onFailure(call: Call<LineResponse?>, t: Throwable) {
                    Log.d("APIQUERY", t.toString())
                }
            })
        }


        fun fetchStations(line: String, stations: MutableLiveData<List<StationResponse>>) {
            val retrofitData = retrofit.getStations(line)

            retrofitData.enqueue(object : Callback<StationResponse?> {
                override fun onResponse(
                    call: Call<StationResponse?>,
                    response: Response<StationResponse?>
                ) {
                    val responseBody = response.body()
                    stations.value = listOf(responseBody!!)
                    Log.d("APIQUERY", responseBody.toString())
                }

                override fun onFailure(call: Call<StationResponse?>, t: Throwable) {
                    Log.d("APIQUERY", t.toString())
                }
            })
        }

    }

}