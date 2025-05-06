package com.example.drivercabinet.controller

import com.example.drivercabinet.model.request.registration.DriverApproveRequest
import com.example.drivercabinet.model.request.registration.DriverLoginRequest
import com.example.drivercabinet.model.request.registration.DriverRegisterRequest
import com.example.drivercabinet.model.response.JwtResponse
import com.example.drivercabinet.service.impl.AuthServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class AuthController(
    private val authService: AuthServiceImpl
) {

    @PostMapping("/register")
    fun register(@RequestBody request: DriverRegisterRequest): ResponseEntity<String> {
        authService.register(request)
        return ResponseEntity.ok("Driver registered. Verification code sent to email.")
    }

    @PostMapping("/auth/confirm")
    fun verify(@RequestBody request: DriverApproveRequest): ResponseEntity<String> {
        authService.verifyCode(request)
        return ResponseEntity.ok("Driver verified successfully")
    }

    @PostMapping("/auth/login")
    fun login(@RequestBody request: DriverLoginRequest): ResponseEntity<JwtResponse> {
        return ResponseEntity.ok(authService.login(request))
    }
}