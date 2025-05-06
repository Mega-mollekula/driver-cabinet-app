package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.Driver
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface DriverDao : JpaRepository<Driver, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Driver?
}
