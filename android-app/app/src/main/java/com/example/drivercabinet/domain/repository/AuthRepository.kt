package com.example.drivercabinet.domain.repository

import com.example.drivercabinet.data.model.ApproveRequest
import com.example.drivercabinet.data.model.JwtResponse
import com.example.drivercabinet.data.model.LoginRequest
import com.example.drivercabinet.data.model.RegisterRequest

interface AuthRepository {
    suspend fun register(request: RegisterRequest): Result<String>
    suspend fun confirm(request: ApproveRequest): Result<String>
    suspend fun login(request: LoginRequest): Result<JwtResponse>
}
