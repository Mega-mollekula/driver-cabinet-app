package com.example.drivercabinet.controller
import com.example.drivercabinet.model.request.ManagerRequest
import com.example.drivercabinet.model.response.ManagerResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.example.drivercabinet.service.ManagerService

@RestController
@RequestMapping("/managers")
class ManagerController(
    val managerService: ManagerService,
) {

    @GetMapping
    fun getAllManagers(): List<ManagerResponse> {
        return managerService.getAll()
    }

    @GetMapping("/{managerId}")
    fun getManagerById(@PathVariable managerId: Long): ManagerResponse {
        return managerService.getById(managerId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createManager(@RequestBody managerRequest: ManagerRequest): ManagerResponse {
        return managerService.create(managerRequest)
    }

    @PutMapping("/{managerId}")
    fun updateManager(
        @PathVariable managerId: Long,
        @RequestBody managerRequest: ManagerRequest
    ): ManagerResponse {
        return managerService.updateProfile(managerId, managerRequest)
    }

    @DeleteMapping("/{managerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteManager(@PathVariable managerId: Long) {
        managerService.delete(managerId)
    }
}