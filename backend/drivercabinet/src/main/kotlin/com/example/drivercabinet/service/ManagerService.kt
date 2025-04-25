package com.example.drivercabinet.service

import com.example.drivercabinet.model.request.ManagerRequest
import com.example.drivercabinet.model.response.ManagerResponse

interface ManagerService {
    fun createManager(request: ManagerRequest): ManagerResponse
    fun getManager(id: Long): ManagerResponse
    fun getAllManagers(): List<ManagerResponse>
}

