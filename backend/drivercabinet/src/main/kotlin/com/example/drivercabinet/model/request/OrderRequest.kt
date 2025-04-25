package com.example.drivercabinet.model.request

import java.time.LocalDateTime

data class OrderRequest(
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime?,
    val driverId: Long,
    val routeId: Long
)
