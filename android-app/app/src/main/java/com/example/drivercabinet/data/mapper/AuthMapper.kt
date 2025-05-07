package com.example.drivercabinet.data.mapper

import com.example.drivercabinet.data.dto.ApproveRequestDto
import com.example.drivercabinet.data.dto.JwtResponseDto
import com.example.drivercabinet.data.dto.LoginRequestDto
import com.example.drivercabinet.data.dto.RegisterRequestDto
import com.example.drivercabinet.data.model.ApproveRequest
import com.example.drivercabinet.data.model.JwtResponse
import com.example.drivercabinet.data.model.LoginRequest
import com.example.drivercabinet.data.model.RegisterRequest

fun RegisterRequest.toDto() = RegisterRequestDto(name, email, phoneNumber, password)
fun LoginRequest.toDto() = LoginRequestDto(email, password)
fun ApproveRequest.toDto() = ApproveRequestDto(email, code)

fun JwtResponseDto.toDomain() = JwtResponse(token)