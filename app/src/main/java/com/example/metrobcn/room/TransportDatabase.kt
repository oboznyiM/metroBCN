package com.example.metrobcn.room

import android.app.Application
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.metrobcn.model.Line
import com.example.metrobcn.model.Station
import java.util.concurrent.Executors

//TODO add station
@Database(version=1, entities = [Station::class, Line::class]
)
abstract class TransportDatabase : RoomDatabase() {
    abstract fun lineDao(): LineDao
    abstract fun stationDao(): StationDao

    companion object {
        @Volatile
        private var INSTANCE: TransportDatabase? = null

        fun getDatabase(context: Context): TransportDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(context, TransportDatabase::class.java, "transport").build()
                INSTANCE = db
                db
            }
        }
        val databaseWriteExecutor = Executors.newFixedThreadPool(2)
    }
}