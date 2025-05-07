package com.example.drivercabinet.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers")
data class DriverEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val phoneNumber: String,
    val email: String,
    val status: String = "OFFLINE",
    val rating: Double = 4.0,
    val referrerId: Long? = null,
    val points: Int = 0,
    val role: String = "DRIVER",
    val password: String,
    val isApproved: Boolean = false,
    val verificationCode: String? = null,
    val createdAt: String? = null,
    val token: String? = null
)