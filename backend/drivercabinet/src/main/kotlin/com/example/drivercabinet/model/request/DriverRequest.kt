package com.example.drivercabinet.model.request

import com.example.drivercabinet.database.entity.DriverStatus

data class DriverRequest(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val status: DriverStatus,
    val rating: Double
)