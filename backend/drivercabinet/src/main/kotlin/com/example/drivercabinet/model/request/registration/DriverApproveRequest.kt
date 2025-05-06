package com.example.drivercabinet.model.request.registration

data class DriverApproveRequest(
    val email: String,
    val code: String
)