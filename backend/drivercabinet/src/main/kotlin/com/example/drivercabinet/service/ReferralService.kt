package com.example.drivercabinet.service

import com.example.drivercabinet.database.entity.OrderStatus
import com.example.drivercabinet.database.repository.DriverDao
import com.example.drivercabinet.database.repository.OrderDao
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReferralService(
    private val driverDao: DriverDao,
    private val orderDao: OrderDao
)  {

    @Transactional
    fun processReferrals() {
        val drivers = driverDao.findAll()

        for (driver in drivers) {
            driver.referrals.forEach { referral ->
                if (referral.createdAt?.isBefore(LocalDateTime.now().minusDays(30)) == true) {
                    val completedOrdersCount = orderDao.countByDriverIdAndStatus(referral.id, OrderStatus.COMPLETED)
                    if (completedOrdersCount >= 5) {
                        driver.points += 500
                        referral.referrer?.referrals?.remove(referral)
                    }
                }
            }
        }
    }
}