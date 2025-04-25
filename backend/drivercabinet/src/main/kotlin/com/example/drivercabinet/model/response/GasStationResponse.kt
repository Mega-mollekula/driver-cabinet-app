package com.example.drivercabinet.model.response

data class GasStationResponse(
    val id: Long,
    val name: String,
    val address: String,
    val fuelTypes: String
)
