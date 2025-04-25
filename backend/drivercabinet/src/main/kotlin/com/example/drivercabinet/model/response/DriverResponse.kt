package com.example.drivercabinet.model.response

import com.example.drivercabinet.database.entity.Driver
import com.example.drivercabinet.database.entity.DriverStatus
import com.example.drivercabinet.database.entity.Order

data class DriverResponse(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val status: DriverStatus,
    val rating: Double,
    val referrals: List<Driver>,
    val email: String,
    val orders: List<Order>
)
