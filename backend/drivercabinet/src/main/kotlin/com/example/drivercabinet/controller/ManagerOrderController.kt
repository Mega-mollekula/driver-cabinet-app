package com.example.drivercabinet.controller

import com.example.drivercabinet.model.request.OrderRequest
import com.example.drivercabinet.model.response.OrderResponse
import com.example.drivercabinet.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/manager/orders")
class ManagerOrderController(
    val orderService: OrderService
) {
    @GetMapping
    fun getAllOrders(): List<OrderResponse> {
        return orderService.getAll()
    }

    @GetMapping("/{orderId}")
    fun getOrderById(@PathVariable orderId: Long): OrderResponse {
        return orderService.getById(orderId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody orderRequest: OrderRequest): OrderResponse {
        return orderService.create(orderRequest)
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrder(@PathVariable orderId: Long) {
        orderService.delete(orderId)
    }
}