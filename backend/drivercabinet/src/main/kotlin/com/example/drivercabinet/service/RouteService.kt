package com.example.drivercabinet.service

import com.example.drivercabinet.model.request.RouteRequest
import com.example.drivercabinet.model.response.RouteResponse

interface RouteService {
    fun createRoute(request: RouteRequest): RouteResponse
    fun getRoute(id: Long): RouteResponse
    fun getAllRoutes(): List<RouteResponse>
}
