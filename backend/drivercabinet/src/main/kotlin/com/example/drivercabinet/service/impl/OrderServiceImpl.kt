package com.example.drivercabinet.service.impl

import com.example.drivercabinet.database.entity.DriverStatus
import com.example.drivercabinet.database.entity.OrderStatus
import com.example.drivercabinet.database.repository.DriverDao
import com.example.drivercabinet.database.repository.OrderDao
import com.example.drivercabinet.database.repository.RouteDao
import com.example.drivercabinet.model.request.OrderRequest
import com.example.drivercabinet.model.response.OrderResponse
import com.example.drivercabinet.service.OrderService
import com.example.drivercabinet.util.OrderMapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderServiceImpl (
    val orderDao: OrderDao,
    val routeDao: RouteDao,
    val mapper: OrderMapper,
) : OrderService {

    override fun getAll(): List<OrderResponse> =
        orderDao.findAll().map { mapper.entityToResponse(it) }

    override fun getById(orderId: Long): OrderResponse =
        mapper.entityToResponse(orderDao.findById(orderId).orElseThrow {
            IllegalArgumentException("Order not found with id $orderId")
        })

    override fun create(orderRequest: OrderRequest): OrderResponse {
        val route = routeDao.findById(orderRequest.routeId)
            .orElseThrow { IllegalArgumentException("Route not found with id ${orderRequest.routeId}") }

        val order = mapper.requestToEntity(orderRequest, route)
        orderDao.save(order)
        return mapper.entityToResponse(order)
    }

    override fun delete(orderId: Long) {
        val order = orderDao.findById(orderId).orElseThrow {
            IllegalArgumentException("Order not found with id $orderId")
        }
        orderDao.delete(order)
    }
}
