package com.example.drivercabinet.service

import com.example.drivercabinet.model.request.OrderRequest
import com.example.drivercabinet.model.response.OrderResponse

interface OrderService {
    fun createOrder(request: OrderRequest): OrderResponse
    fun getOrder(id: Long): OrderResponse
    fun getAllOrders(): List<OrderResponse>
}
