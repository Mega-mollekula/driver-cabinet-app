package com.example.drivercabinet.model.request

import com.example.drivercabinet.database.entity.FuelType

data class GasStationRequest(
    val address: String,
    val fuelTypes: Set<FuelType>
)
