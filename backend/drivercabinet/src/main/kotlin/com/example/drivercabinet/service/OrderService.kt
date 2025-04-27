package com.example.drivercabinet.service

import com.example.drivercabinet.model.request.OrderRequest
import com.example.drivercabinet.model.response.OrderResponse

interface OrderService {
    fun getAll(): List<OrderResponse>
    fun getById(orderId: Long): OrderResponse
    fun create(orderRequest: OrderRequest): OrderResponse
    fun delete(orderId: Long)
    fun completeOrder(orderId: Long): OrderResponse
    fun assignOrderToDriver(orderId: Long, driverId: Long): OrderResponse
}
