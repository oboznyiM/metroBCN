package com.example.metrobcn.room

import androidx.lifecycle.LiveData
import com.example.metrobcn.model.Station

class StationRepository(private val stationDao: StationDao) {
    val allStations : LiveData<List<Station>> = stationDao.getStations()

    fun insert(station: Station) {
        TransportDatabase.databaseWriteExecutor.execute {
            stationDao.insert(station)
        }
    }
}