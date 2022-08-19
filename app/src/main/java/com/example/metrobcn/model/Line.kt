package com.example.metrobcn.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.io.Serializable

@Parcelize
data class LineToPass (
    val id: Int,
    val color: String,
    val title: String
) : Parcelable

@Entity(tableName = "Line")
data class Line(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "title")
    val title: String
)