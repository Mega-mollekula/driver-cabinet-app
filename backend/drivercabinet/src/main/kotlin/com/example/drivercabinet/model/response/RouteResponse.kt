package com.example.drivercabinet.model.response

import com.example.drivercabinet.database.entity.GasStation

data class RouteResponse(
    val id: Long,
    val origin: String,
    val destination: String,
    val gasStations: List<GasStation>
)
