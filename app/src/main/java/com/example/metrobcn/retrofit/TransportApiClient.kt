package com.example.metrobcn.retrofit

import com.example.metrobcn.model.LineResponse
import com.example.metrobcn.model.Station
import com.example.metrobcn.model.StationResponse
import com.example.metrobcn.model.TimelineResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TransportApiClient {
    @GET("ibus/stops/2617?app_id=eaecba9f&app_key=f717d352b6c6df728a8f972ce50f8277&codi_parada=13")
    fun getData(): Call<TimelineResponse>

    @GET("transit/linies/bus/{code}/parades?app_id=eaecba9f&app_key=f717d352b6c6df728a8f972ce50f8277")
    fun getStations(@Path("code") code: String): Call<StationResponse>

    @GET("transit/linies/bus?app_id=eaecba9f&app_key=f717d352b6c6df728a8f972ce50f8277")
    fun getLines(): Call<LineResponse>
}