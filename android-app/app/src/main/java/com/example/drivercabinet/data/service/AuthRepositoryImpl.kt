package com.example.drivercabinet.data.service

import com.example.drivercabinet.common.api.AuthApi
import com.example.drivercabinet.data.mapper.toDomain
import com.example.drivercabinet.data.mapper.toDto
import com.example.drivercabinet.data.model.ApproveRequest
import com.example.drivercabinet.data.model.JwtResponse
import com.example.drivercabinet.data.model.LoginRequest
import com.example.drivercabinet.data.model.RegisterRequest
import com.example.drivercabinet.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun register(request: RegisterRequest): Result<String> {
        return try {
            val response = api.register(request.toDto())
            if (response.isSuccessful) Result.success(response.body() ?: "")
            else Result.failure(Exception(response.errorBody()?.string()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun confirm(request: ApproveRequest): Result<String> {
        return try {
            val response = api.confirm(request.toDto())
            if (response.isSuccessful) Result.success(response.body() ?: "")
            else Result.failure(Exception(response.errorBody()?.string()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(request: LoginRequest): Result<JwtResponse> {
        return try {
            val response = api.login(request.toDto())
            if (response.isSuccessful) Result.success(response.body()!!.toDomain())
            else Result.failure(Exception(response.errorBody()?.string()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
