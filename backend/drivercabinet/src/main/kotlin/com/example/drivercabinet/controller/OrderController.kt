package com.example.drivercabinet.controller
import com.example.drivercabinet.model.request.OrderRequest
import com.example.drivercabinet.model.response.OrderResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.example.drivercabinet.service.OrderService

@RestController
@RequestMapping("/orders")
class OrderController(
    val orderService: OrderService,
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

    @PostMapping("/{orderId}/complete")
    fun completeOrder(@PathVariable orderId: Long): OrderResponse {
        return orderService.completeOrder(orderId)
    }

    @PostMapping("/{orderId}/assign/{driverId}")
    fun assignOrderToDriver(@PathVariable orderId: Long, @PathVariable driverId: Long): OrderResponse {
        return orderService.assignOrderToDriver(orderId, driverId)
    }
}