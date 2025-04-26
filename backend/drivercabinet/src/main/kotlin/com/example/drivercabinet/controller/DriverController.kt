package com.example.drivercabinet.controller
import com.example.drivercabinet.database.entity.DriverStatus
import com.example.drivercabinet.model.request.DriverRequest
import com.example.drivercabinet.model.response.DriverResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.example.drivercabinet.service.DriverService

@RestController
@RequestMapping("/drivers")
class DriverController(
    val driverService: DriverService,
) {

    @GetMapping
    fun getAllDrivers(): List<DriverResponse> {
        return driverService.getAll()
    }

    // Получение водителя по id
    @GetMapping("/{driverId}")
    fun getDriverById(@PathVariable driverId: Long): DriverResponse {
        return driverService.getById(driverId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDriver(@RequestBody driverRequest: DriverRequest): DriverResponse {
        return driverService.create(driverRequest)
    }

    @PutMapping("/{driverId}")
    fun updateDriver(
        @PathVariable driverId: Long,
        @RequestBody driverRequest: DriverRequest
    ): DriverResponse {
        return driverService.updateProfile(driverId, driverRequest)
    }

    @PatchMapping("/{driverId}/status")
    fun updateDriverStatus(
        @PathVariable driverId: Long,
        @RequestParam newStatus: String
    ): DriverResponse {
        val status = try {
            DriverStatus.valueOf(newStatus.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid status: $newStatus")
        }
        return driverService.updateStatus(driverId, status)
    }

    @DeleteMapping("/{driverId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDriver(@PathVariable driverId: Long) {
        driverService.delete(driverId)
    }
}