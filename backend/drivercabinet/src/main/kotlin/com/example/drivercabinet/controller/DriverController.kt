package com.example.drivercabinet.controller
import com.example.drivercabinet.database.entity.DriverStatus
import com.example.drivercabinet.model.response.DriverResponse
import org.springframework.web.bind.annotation.*
import com.example.drivercabinet.service.DriverService

@RestController
@RequestMapping("/drivers")
class DriverController(
    val driverService: DriverService,
) {

    @PatchMapping("/{driverId}/status")
    fun updateDriverStatus(@PathVariable driverId: Long, @RequestBody newStatus: String): DriverResponse {
        val status = try {
            DriverStatus.valueOf(newStatus.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid status: $newStatus")
        }
        return driverService.updateStatus(driverId, status)
    }

    @PostMapping("/orders/{orderId}/complete")
    fun completeOrder(@PathVariable orderId: Long) : DriverResponse {
        return driverService.completeOrder(orderId)
    }

    @PostMapping("/{driverId}/orders/{orderId}/assign")
    fun assignOrder(@PathVariable driverId: Long, @PathVariable orderId: Long) : DriverResponse {
        return driverService.assignOrderToDriver(driverId, orderId)
    }

    @GetMapping("/{driverId}")
    fun getDriverById(@PathVariable driverId: Long): DriverResponse {
        return driverService.getById(driverId)
    }

//        @DeleteMapping("/{driverId}")
//        @ResponseStatus(HttpStatus.NO_CONTENT)
//        fun deleteDriver(@PathVariable driverId: Long) {
//            driverService.delete(driverId)
//        }

}