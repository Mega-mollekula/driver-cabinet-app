package com.example.drivercabinet.model.response

import com.example.drivercabinet.database.entity.Driver
import com.example.drivercabinet.database.entity.Route
import java.time.LocalDateTime

data class OrderResponse(
    val id: Long,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime?,
    val driver: Driver,
    val route: Route
)
