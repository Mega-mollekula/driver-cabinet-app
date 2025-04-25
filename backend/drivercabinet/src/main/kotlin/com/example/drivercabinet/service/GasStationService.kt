package com.example.drivercabinet.service

import com.example.drivercabinet.model.request.GasStationRequest
import com.example.drivercabinet.model.response.GasStationResponse

interface GasStationService {
    fun createGasStation(request: GasStationRequest): GasStationResponse
    fun getGasStation(id: Long): GasStationResponse
    fun getAllGasStations(): List<GasStationResponse>
}
