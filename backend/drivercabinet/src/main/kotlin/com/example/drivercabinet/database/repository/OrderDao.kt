package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.Order
import com.example.drivercabinet.database.entity.OrderStatus
import org.springframework.data.repository.CrudRepository

interface OrderDao : CrudRepository<Order, Long> {
    fun countByDriverIdAndStatus(driverId: Long, status: OrderStatus): Int
}
