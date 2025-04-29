package com.example.drivercabinet.controller
import com.example.drivercabinet.model.request.GasStationRequest
import com.example.drivercabinet.model.response.GasStationResponse
import com.example.drivercabinet.service.GasStationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/gas-stations")
class GasStationController(
    val gasStationService: GasStationService,
) {

    @GetMapping
    fun getAllStations(): List<GasStationResponse> {
        return gasStationService.getAll()
    }

    @GetMapping("/{gasStationId}")
    fun getStationById(@PathVariable gasStationId: Long): GasStationResponse {
        return gasStationService.getById(gasStationId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createStation(@RequestBody gasStationRequest: GasStationRequest): GasStationResponse {
        return gasStationService.create(gasStationRequest)
    }

    @PutMapping("/{gasStationId}")
    fun updateStation(@PathVariable gasStationId: Long, @RequestBody gasStationRequest: GasStationRequest): GasStationResponse {
        return gasStationService.update(gasStationId, gasStationRequest)
    }

    @DeleteMapping("/{gasStationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStation(@PathVariable gasStationId: Long) {
        gasStationService.delete(gasStationId)
    }
}