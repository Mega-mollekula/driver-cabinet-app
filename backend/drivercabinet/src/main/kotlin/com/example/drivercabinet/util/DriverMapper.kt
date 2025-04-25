package com.example.drivercabinet.util

import com.example.drivercabinet.database.entity.Driver
import com.example.drivercabinet.model.request.DriverRequest
import com.example.drivercabinet.model.response.DriverResponse
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
@Component
class DriverMapper {
    fun entityToResponse(entity: Driver) : DriverResponse =
        DriverResponse(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            phoneNumber = entity.phoneNumber,
            status = entity.status,
            rating = entity.rating,
            referrals = entity.referrals,
            orders = entity.orders
        )

    fun requestToEntity(request: DriverRequest): Driver =
        Driver(
            name = request.name,
            phoneNumber = request.phoneNumber,
            email = request.email,
            status = request.status,
            rating = request.rating
        )
}
