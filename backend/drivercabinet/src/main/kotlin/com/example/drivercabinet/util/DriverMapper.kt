package com.example.drivercabinet.util

import com.example.drivercabinet.database.entity.Driver
import com.example.drivercabinet.model.request.DriverRequest
import com.example.drivercabinet.model.response.DriverResponse
import org.springframework.stereotype.Component

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
            orders = entity.orders,
            points = entity.points,
            referrer = entity.referrer
        )

    fun requestToEntity(request: DriverRequest): Driver =
        Driver(
            name = request.name,
            phoneNumber = request.phoneNumber,
            email = request.email,
            password = request.password,
        )
}
