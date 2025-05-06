package com.example.drivercabinet.model.request.registration

data class DriverRegisterRequest(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val password: String
)
