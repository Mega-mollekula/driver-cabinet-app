package com.example.drivercabinet.service.impl

import com.example.drivercabinet.database.repository.GasStationDao
import com.example.drivercabinet.database.repository.RouteDao
import com.example.drivercabinet.model.request.RouteRequest
import com.example.drivercabinet.model.response.RouteResponse
import com.example.drivercabinet.service.RouteService
import com.example.drivercabinet.util.RouteMapper
import org.springframework.stereotype.Service

@Service
class RoutServiceImpl (
    val dao: RouteDao,
    val gasStationDao: GasStationDao,
    val mapper: RouteMapper,
) : RouteService {

    override fun getAll(): List<RouteResponse> =
        dao.findAll().map { mapper.entityToResponse(it) }

    override fun getById(routeId: Long): RouteResponse =
        mapper.entityToResponse(dao.findById(routeId).orElseThrow {
            IllegalArgumentException("Route not found with id $routeId")
        })

    override fun update(routeId: Long, routeRequest: RouteRequest): RouteResponse {
        val route = dao.findById(routeId).orElseThrow {
            IllegalArgumentException("Driver not found with id $routeId")
        }
        route.origin = route.origin
        route.destination = route.destination
        route.gasStations = route.gasStations

        dao.save(route)

        return mapper.entityToResponse(route)
    }

    override fun create(routeRequest: RouteRequest): RouteResponse {
        val gasStations = gasStationDao.findAllById(routeRequest.gasStationIds).toMutableList()

        val route = mapper.requestToEntity(routeRequest, gasStations)

        dao.save(route)
        return mapper.entityToResponse(route)
    }

    override fun delete(routeId: Long) {
        val route = dao.findById(routeId).orElseThrow {
            IllegalArgumentException("Route not found with id $routeId")
        }
        dao.delete(route)
    }


}







