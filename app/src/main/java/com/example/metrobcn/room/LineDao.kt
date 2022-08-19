package com.example.metrobcn.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.metrobcn.model.Line

@Dao
interface LineDao {
    @Query("SELECT * from Line")
    fun getLines() : LiveData<List<Line>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(line: Line)

    @Query("SELECT * FROM Line WHERE id=:id")
    fun findLine(id: Int) : LiveData<List<Line>>
}