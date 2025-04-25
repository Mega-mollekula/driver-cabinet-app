package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface OrderDao : CrudRepository<Order, Long> {
}
