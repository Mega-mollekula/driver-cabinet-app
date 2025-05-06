package com.example.drivercabinet.service.impl

import com.example.drivercabinet.database.entity.Driver
import com.example.drivercabinet.database.entity.Role
import com.example.drivercabinet.database.repository.DriverDao
import com.example.drivercabinet.model.request.registration.DriverApproveRequest
import com.example.drivercabinet.model.request.registration.DriverLoginRequest
import com.example.drivercabinet.model.request.registration.DriverRegisterRequest
import com.example.drivercabinet.model.response.JwtResponse
import com.example.drivercabinet.service.EmailService
import com.example.drivercabinet.service.JwtService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.naming.AuthenticationException

@Service
class AuthServiceImpl(
    private val driverDao: DriverDao,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService, // предположим, что он уже есть
    private val jwtService: JwtService // предположим, что он уже есть
) {

    fun register(request: DriverRegisterRequest) {
        if (driverDao.existsByEmail(request.email)) {
            throw IllegalArgumentException("Email already registered")
        }

        val verificationCode = generateCode()

        val driver = Driver(
            name = request.name,
            email = request.email,
            phoneNumber = request.phoneNumber,
            password = passwordEncoder.encode(request.password),
            isApproved = false,
            verificationCode = verificationCode,
            role = Role.DRIVER
        )

        driverDao.save(driver)
        emailService.sendVerificationCode(driver.email, verificationCode)
    }

    fun verifyCode(request: DriverApproveRequest) {
        val driver = driverDao.findByEmail(request.email)
            ?: throw IllegalArgumentException("Driver not found")

        if (driver.verificationCode != request.code) {
            throw IllegalArgumentException("Invalid verification code")
        }

        driver.isApproved = true
        driver.verificationCode = null
        driverDao.save(driver)
    }

    fun login(request: DriverLoginRequest): JwtResponse {
        val driver = driverDao.findByEmail(request.email)
            ?: throw IllegalArgumentException("Driver not found")

        if (!driver.isApproved) {
            throw AuthenticationException("Driver not approved")
        }

        if (!passwordEncoder.matches(request.password, driver.password)) {
            throw IllegalArgumentException("Invalid credentials")
        }

        val token = jwtService.generateToken(driver)
        return JwtResponse(token)
    }

    private fun generateCode(): String {
        return UUID.randomUUID().toString().substring(0, 6).uppercase()
    }
}
