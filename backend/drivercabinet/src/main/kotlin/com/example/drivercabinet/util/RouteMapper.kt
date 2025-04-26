package com.example.drivercabinet.util

import com.example.drivercabinet.database.entity.GasStation
import com.example.drivercabinet.database.entity.Route
import com.example.drivercabinet.model.request.RouteRequest
import com.example.drivercabinet.model.response.RouteResponse
import org.springframework.stereotype.Component

@Component
class RouteMapper {
    fun entityToResponse(entity: Route) : RouteResponse =
        RouteResponse(
            id = entity.id,
            origin = entity.origin,
            destination = entity.destination,
            gasStations = entity.gasStations,
        )

    fun requestToEntity(request: RouteRequest, gasStations: MutableList<GasStation>): Route =
        Route(
            origin = request.origin,
            destination = request.destination,
            gasStations = gasStations
        )
}
