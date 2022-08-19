package com.example.metrobcn.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.metrobcn.model.Station

@Dao
interface StationDao {
    @Query("SELECT * from station")
    fun getStations() : LiveData<List<Station>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(station: Station)
}