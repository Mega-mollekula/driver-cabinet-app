package com.example.drivercabinet.util

import com.example.drivercabinet.database.entity.Manager
import com.example.drivercabinet.model.request.ManagerRequest
import com.example.drivercabinet.model.response.ManagerResponse
import org.springframework.stereotype.Component

@Component
class ManagerMapper {
    fun entityToResponse(entity: Manager) : ManagerResponse =
        ManagerResponse(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            phoneNumber = entity.phoneNumber,
        )

    fun requestToEntity(request: ManagerRequest): Manager =
        Manager(
            name = request.name,
            phoneNumber = request.phoneNumber,
            email = request.email,
        )
}