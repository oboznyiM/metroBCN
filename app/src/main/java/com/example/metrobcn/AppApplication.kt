package com.example.metrobcn

import android.app.Application
import com.example.metrobcn.room.LineRepository
import com.example.metrobcn.room.StationRepository
import com.example.metrobcn.room.TransportDatabase

class AppApplication: Application() {

    val database by lazy { TransportDatabase.getDatabase(this) }

    val lineRepository by lazy { LineRepository(database.lineDao()) }

    val stationRepository by lazy { StationRepository(database.stationDao()) }


}