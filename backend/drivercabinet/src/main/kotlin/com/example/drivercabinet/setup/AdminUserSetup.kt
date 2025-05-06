package com.example.drivercabinet.setup

import com.example.drivercabinet.database.entity.Driver
import com.example.drivercabinet.database.entity.Role
import com.example.drivercabinet.database.repository.DriverDao
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class AdminUserSetup(
    private val driverDao: DriverDao,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val adminExists = driverDao.findByEmail("admin@example.com") != null
        if (!adminExists) {
            val hashedPassword = passwordEncoder.encode("192837")

            // Создаем администратора
            val admin = Driver(
                email = "admin@example.com",
                password = hashedPassword,
                role = Role.ADMIN,
                name = "Artyom Solovyev",
                phoneNumber = "+79099166313",
                isApproved = true
            )
            driverDao.save(admin)
            println("Admin user created with email: admin@example.com")
        }
    }
}
