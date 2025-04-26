package com.example.drivercabinet.service

import com.example.drivercabinet.model.request.GasStationRequest
import com.example.drivercabinet.model.response.GasStationResponse

interface GasStationService {
    fun getAll(): List<GasStationResponse>
    fun getById(gasStationId: Long): GasStationResponse
    fun update(gasStationId: Long, gasStationRequest: GasStationRequest): GasStationResponse
    fun create(gasStationId: GasStationRequest): GasStationResponse
    fun delete(gasStationId: Long)
}
