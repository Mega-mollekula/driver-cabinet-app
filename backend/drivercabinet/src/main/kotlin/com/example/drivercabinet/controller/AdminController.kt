package com.example.drivercabinet.controller

import com.example.drivercabinet.model.request.RoleChangeRequest
import com.example.drivercabinet.service.DriverService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    private val driverService: DriverService
) {
    @PostMapping("/role-change")
    fun changeRole(@RequestBody request: RoleChangeRequest): ResponseEntity<String> {
        driverService.changeRole(request.driverId, request.newRole)
        return ResponseEntity.ok("Role updated successfully.")
    }
}