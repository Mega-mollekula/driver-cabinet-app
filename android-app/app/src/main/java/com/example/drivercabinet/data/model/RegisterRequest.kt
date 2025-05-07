package com.example.drivercabinet.data.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val password: String
)
