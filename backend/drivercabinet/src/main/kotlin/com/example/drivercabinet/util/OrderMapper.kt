package com.example.drivercabinet.util

import com.example.drivercabinet.database.entity.Driver
import com.example.drivercabinet.database.entity.Order
import com.example.drivercabinet.database.entity.Route
import com.example.drivercabinet.model.request.OrderRequest
import com.example.drivercabinet.model.response.OrderResponse
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OrderMapper {
    fun entityToResponse(entity: Order) : OrderResponse =
        OrderResponse(
            id = entity.id,
            description = entity.description,
            startTime = entity.startTime,
            endTime = entity.endTime,
            driver = entity.driver,
            route = entity.route,
            status = entity.status,
        )

    fun requestToEntity(request: OrderRequest, route: Route): Order =
        Order(
            description = request.description,
            startTime = request.startTime ?: LocalDateTime.now(),
            endTime = request.endTime,
            route = route
        )
}