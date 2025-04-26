package com.example.drivercabinet.service

import com.example.drivercabinet.model.request.ManagerRequest
import com.example.drivercabinet.model.response.ManagerResponse

interface ManagerService {
    fun getAll(): List<ManagerResponse>
    fun getById(managerId: Long): ManagerResponse
    fun updateProfile(managerId: Long, managerRequest: ManagerRequest): ManagerResponse
    fun create(managerRequest: ManagerRequest): ManagerResponse
    fun delete(managerId: Long)
}

