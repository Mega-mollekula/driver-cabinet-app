package com.example.drivercabinet.service.impl

import com.example.drivercabinet.database.entity.DriverStatus
import com.example.drivercabinet.database.entity.OrderStatus
import com.example.drivercabinet.database.repository.DriverDao
import com.example.drivercabinet.database.repository.OrderDao
import com.example.drivercabinet.model.request.DriverRequest
import com.example.drivercabinet.model.response.DriverResponse
import com.example.drivercabinet.service.DriverService
import com.example.drivercabinet.util.DriverMapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DriverServiceImpl(
    val driverDao: DriverDao,
    val orderDao: OrderDao,
    val mapper: DriverMapper,
): DriverService {
    override fun updateStatus(driverId: Long, newStatus: DriverStatus): DriverResponse {
        val driver = driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        driver.status = newStatus
        if (newStatus == DriverStatus.EMERGENCY) {
            driver.rating -= 0.1
        }
        driverDao.save(driver)
        return mapper.entityToResponse(driver)
    }

    override fun getAll(): List<DriverResponse> =
        driverDao.findAll().map { mapper.entityToResponse(it) }

    override fun getById(driverId: Long): DriverResponse =
        mapper.entityToResponse(driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        })

    override fun updateRating(driverId: Long, rating: Double): DriverResponse {
        val driver = driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        driver.rating = rating
        driverDao.save(driver)

        return mapper.entityToResponse(driver)
    }

    override fun delete(driverId: Long) {
        val driver = driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        driverDao.delete(driver)
    }

    @Transactional
    override fun create(driverRequest: DriverRequest, referrerId: Long?): DriverResponse {
        val driver = mapper.requestToEntity(driverRequest)
        if (referrerId != null) {
            val referrer = driverDao.findById(referrerId).orElseThrow {
                IllegalArgumentException("Referrer not found with id $referrerId")
            }
            driver.referrer = referrer
            referrer.referrals.add(driver)
        }
        driverDao.save(driver)
        return mapper.entityToResponse(driver)
    }

    @Transactional
    override fun completeOrder(orderId: Long): DriverResponse {
        val order = orderDao.findById(orderId).orElseThrow {
            IllegalArgumentException("Order not found with id $orderId")
        }
        when (order.status) {
            OrderStatus.IN_PROGRESS -> {
                order.endTime = LocalDateTime.now()
                order.status = OrderStatus.COMPLETED
            }
            OrderStatus.PENDING -> {
                throw IllegalArgumentException("This order now pending")
            }
            OrderStatus.COMPLETED -> {
                throw IllegalArgumentException("This order now only available")
            }
        }
        val driver = order.driver!!
        driver.rating = (driver.rating + 0.05).coerceAtMost(5.0)
        driverDao.save(driver)
        orderDao.save(order)
        return mapper.entityToResponse(driver)
    }

    @Transactional
    override fun assignOrderToDriver(orderId: Long, driverId: Long): DriverResponse {
        val order = orderDao.findById(orderId).orElseThrow {
            IllegalArgumentException("Order not found with id $orderId")
        }
        if (order.status == OrderStatus.COMPLETED || order.status == OrderStatus.IN_PROGRESS) {
            throw IllegalStateException("Cannot assign an order.")
        }
        val driver = driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        if (driver.status != DriverStatus.AVAILABLE) {
            throw IllegalStateException("Driver is not available.")
        }
        order.driver = driver
        order.status = OrderStatus.IN_PROGRESS
        orderDao.save(order)
        return mapper.entityToResponse(driver)
    }
}
