package com.example.drivercabinet.data.model

data class DriverResponse(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String,
    val status: String,
    val role: String
)
