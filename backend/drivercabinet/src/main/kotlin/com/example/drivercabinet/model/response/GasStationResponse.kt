package com.example.drivercabinet.model.response

import com.example.drivercabinet.database.entity.FuelType

data class GasStationResponse(
    val id: Long,
    val address: String,
    val fuelTypes: Set<FuelType>
)
