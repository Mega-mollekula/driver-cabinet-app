package com.example.drivercabinet.service

import com.example.drivercabinet.database.entity.DriverStatus
import com.example.drivercabinet.model.request.DriverRequest
import com.example.drivercabinet.model.response.DriverResponse

interface DriverService {
    fun updateStatus(driverId: Long, newStatus: DriverStatus): DriverResponse
    fun getAll(): List<DriverResponse>
    fun getById(driverId: Long): DriverResponse
    fun updateProfile(driverId: Long, driverRequest: DriverRequest): DriverResponse
    fun create(driverRequest: DriverRequest): DriverResponse
    fun delete(driverId: Long)
}
