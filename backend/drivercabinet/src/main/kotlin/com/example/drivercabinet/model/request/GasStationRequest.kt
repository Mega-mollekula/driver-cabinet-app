package com.example.drivercabinet.model.request

data class GasStationRequest(
    val name: String,
    val address: String,
    val fuelTypes: String
)
