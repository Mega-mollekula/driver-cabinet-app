package com.example.drivercabinet.service

import com.example.drivercabinet.model.request.RouteRequest
import com.example.drivercabinet.model.response.RouteResponse

interface RouteService {
    fun getAll(): List<RouteResponse>
    fun getById(routeId: Long): RouteResponse
    fun update(routeId: Long, routeRequest: RouteRequest): RouteResponse
    fun create(routeRequest: RouteRequest): RouteResponse
    fun delete(routeId: Long)
}
