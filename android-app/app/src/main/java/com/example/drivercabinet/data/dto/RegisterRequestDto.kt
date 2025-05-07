package com.example.drivercabinet.data.dto

data class RegisterRequestDto(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val password: String
)