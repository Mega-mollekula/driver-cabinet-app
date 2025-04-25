package com.example.drivercabinet.model.request

data class RouteRequest(
    val origin: String,
    val destination: String,
    val gasStationIds: List<Long>
)
