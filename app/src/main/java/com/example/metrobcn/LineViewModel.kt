package com.example.metrobcn

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metrobcn.model.StationResponse
import com.example.metrobcn.retrofit.TransportApiProvider
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.metrobcn.room.LineRepository
import com.example.metrobcn.room.TransportDatabase

class LineViewModel : ViewModel() {
    val stations: MutableLiveData<List<StationResponse>> = MutableLiveData(emptyList())

    fun getStationsList(line: String) {
        TransportApiProvider.fetchStations(line, stations)
    }
}