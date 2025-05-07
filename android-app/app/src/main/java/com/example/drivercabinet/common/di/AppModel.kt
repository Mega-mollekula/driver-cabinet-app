package com.example.drivercabinet.common.di

import com.example.drivercabinet.common.api.AuthApi
import com.example.drivercabinet.common.api.RetrofitClient
import com.example.drivercabinet.data.service.AuthRepositoryImpl
import com.example.drivercabinet.presentation.viewmodel.AuthViewModel

object AppModule {
    private val authApi = RetrofitClient.instance.create(AuthApi::class.java)
    private val authRepository = AuthRepositoryImpl(authApi)

    val authViewModel = AuthViewModel(authRepository)
}
