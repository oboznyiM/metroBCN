package com.example.metrobcn.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StationToPass (
    val id: Int,
    val title: String,
    val lineId: Int
) : Parcelable


@Entity
data class Station (
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "lineId")
    val lineId: Int
)