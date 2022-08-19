package com.example.metrobcn.model

data class StationResponse(
    val crs: CrsX,
    val features: List<FeatureX>,
    val numberMatched: Int,
    val numberReturned: Int,
    val timeStamp: String,
    val totalFeatures: Int,
    val type: String
)