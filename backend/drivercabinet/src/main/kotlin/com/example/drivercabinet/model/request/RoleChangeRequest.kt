package com.example.drivercabinet.model.request

import com.example.drivercabinet.database.entity.Role

data class RoleChangeRequest(
    val driverId: Long,
    val newRole: Role
)