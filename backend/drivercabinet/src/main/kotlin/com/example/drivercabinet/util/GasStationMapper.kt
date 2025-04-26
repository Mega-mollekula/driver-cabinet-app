package com.example.drivercabinet.util

import com.example.drivercabinet.database.entity.GasStation
import com.example.drivercabinet.model.request.GasStationRequest
import com.example.drivercabinet.model.response.GasStationResponse
import org.springframework.stereotype.Component

@Component
class GasStationMapper () {
    fun entityToResponse(entity: GasStation) : GasStationResponse =
        GasStationResponse(
            id = entity.id,
            address = entity.address,
            fuelTypes = entity.fuelTypes,
        )

    fun requestToEntity(request: GasStationRequest): GasStation =
        GasStation(
            address = request.address,
            fuelTypes = request.fuelTypes,
        )
}


