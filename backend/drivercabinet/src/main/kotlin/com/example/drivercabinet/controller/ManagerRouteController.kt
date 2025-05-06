package com.example.drivercabinet.controller

import com.example.drivercabinet.model.request.RouteRequest
import com.example.drivercabinet.model.response.RouteResponse
import com.example.drivercabinet.service.RouteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("manager/routes")
class ManagerRouteController (
    val routeService: RouteService,
){
    @GetMapping
    fun getAllRoutes(): List<RouteResponse> {
        return routeService.getAll()
    }

    @GetMapping("/{routeId}")
    fun getRouteById(@PathVariable routeId: Long): RouteResponse {
        return routeService.getById(routeId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRout(@RequestBody routeRequest: RouteRequest): RouteResponse {
        return routeService.create(routeRequest)
    }

    @PutMapping("/{routeId}")
    fun updateRoute(@PathVariable routeId: Long, @RequestBody routeRequest: RouteRequest): RouteResponse {
        return routeService.update(routeId, routeRequest)
    }

    @DeleteMapping("/{routeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRoute(@PathVariable routeId: Long) {
        routeService.delete(routeId)
    }
}