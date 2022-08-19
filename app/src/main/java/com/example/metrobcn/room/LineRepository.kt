package com.example.metrobcn.room

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.metrobcn.model.Line

class LineRepository(private val lineDao: LineDao) {

    val allLines: LiveData<List<Line>> = lineDao.getLines()

    fun insert(line: Line) {
        TransportDatabase.databaseWriteExecutor.execute {
            lineDao.insert(line)
        }
    }

    fun getLineById(id: Int) : LiveData<List<Line>> {
        Log.d("APIQUER", id.toString())
        return lineDao.findLine(id)
    }
}