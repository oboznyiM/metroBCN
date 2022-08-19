package com.example.metrobcn

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.metrobcn.model.Line
import com.example.metrobcn.model.Station
import com.example.metrobcn.retrofit.TransportApiProvider
import com.example.metrobcn.room.LineDao
import com.example.metrobcn.room.LineRepository
import com.example.metrobcn.room.StationRepository
import com.example.metrobcn.room.TransportDatabase

class MainViewModel(application: Application): ViewModel(){

    val lineRepository: LineRepository
    //val stationRepository: StationRepository
    val lines: LiveData<List<Line>>

   init {
       val db = TransportDatabase.getDatabase(application)
       val lineDao = db.lineDao()
       lineRepository = LineRepository(lineDao)
       lines = lineRepository.allLines

        //stationRepository = application.stationRepository

        //lines = lineRepository.allLines
    }

    fun getLineById(id: Int) : LiveData<List<Line>> {
        return lineRepository.getLineById(id)
    }

    fun updateLines() {
        TransportApiProvider.fetchLines(this)
    }

    /*fun getLinesFromDatabase(): LiveData<List<Line>> {
        return lineRepository.allLines
    }*/

    fun addLine(line: Line) {
        lineRepository.insert(line)
    }
/*
    fun getStationsFromDatabase(): LiveData<List<Station>> {
        return stationRepository.allStations
    }

    fun addStation(station: Station) {
        stationRepository.insert(station)
    }*/
}