package com.example.metrobcn.model

import com.google.gson.annotations.SerializedName

data class Ibu(
    val destination: String,
    val line: String,
    val routeId: String,
    @SerializedName("t-in-min")
    val tInMin: Int,

    @SerializedName("t-in-s")
    val tInS: Int,

    @SerializedName("text-ca")
    val textCa: String
)