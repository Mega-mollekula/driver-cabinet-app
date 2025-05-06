package com.example.drivercabinet.model.request

data class DriverRequest(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
)