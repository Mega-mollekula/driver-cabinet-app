package com.example.drivercabinet.common.api

import com.example.drivercabinet.data.dto.ApproveRequestDto
import com.example.drivercabinet.data.dto.JwtResponseDto
import com.example.drivercabinet.data.dto.LoginRequestDto
import com.example.drivercabinet.data.dto.RegisterRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    suspend fun register(@Body request: RegisterRequestDto): Response<String>

    @POST("auth/confirm")
    suspend fun confirm(@Body request: ApproveRequestDto): Response<String>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): Response<JwtResponseDto>
}
