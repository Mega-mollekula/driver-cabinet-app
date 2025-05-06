package com.example.drivercabinet.controller

import com.example.drivercabinet.model.response.DriverResponse
import com.example.drivercabinet.service.DriverService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/manager/drivers")
class ManagerDriverController(
    private val driverService: DriverService
) {
    @PostMapping("/{driverId}/orders/{orderId}/assign")
    fun assignOrder(@PathVariable driverId: Long, @PathVariable orderId: Long) : DriverResponse {
        return driverService.assignOrderToDriver(driverId, orderId)
    }

    @GetMapping
    fun getAllDrivers(): List<DriverResponse> {
        return driverService.getAll()
    }

    @GetMapping("/{driverId}")
    fun getDriverById(@PathVariable driverId: Long): DriverResponse {
        return driverService.getById(driverId)
    }

    @PostMapping("/orders/{orderId}/complete")
    fun completeOrder(@PathVariable orderId: Long) : DriverResponse {
        return driverService.completeOrder(orderId)
    }

}